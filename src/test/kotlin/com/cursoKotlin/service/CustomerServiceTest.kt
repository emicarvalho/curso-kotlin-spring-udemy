package com.cursoKotlin.service

import com.cursoKotlin.enuns.CustomerStatus
import com.cursoKotlin.enuns.Errors
import com.cursoKotlin.enuns.Roles
import com.cursoKotlin.model.CustomerModel
import com.cursoKotlin.repository.CustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import com.cursoKotlin.exception.NotFoundException
import com.cursoKotlin.helper.buildCustomer
import io.mockk.impl.annotations.SpyK
import io.mockk.just
import io.mockk.runs
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK
    private lateinit var customerRepository: CustomerRepository

    @MockK
    private lateinit var bookService: BookService

    @MockK
    private lateinit var bCrypt: BCryptPasswordEncoder

    @InjectMockKs
    @SpyK
    private lateinit var customerService: CustomerService

    @Test
    fun `should return all customers`() {

        val fakeCustomers = listOf(buildCustomer(), buildCustomer())

        every { customerRepository.findAll() } returns fakeCustomers

        val customers = customerService.getAll(null)

        assertEquals(fakeCustomers, customers)
        verify(exactly = 1) { customerRepository.findAll() }
        verify(exactly = 0) { customerRepository.findByNameContaining(any()) }
    }

    @Test
    fun `should return customers when name is informed`() {
        val name = UUID.randomUUID().toString()
        val fakeCustomers = listOf(buildCustomer(), buildCustomer())

        every { customerRepository.findByNameContaining(name) } returns fakeCustomers

        val customers = customerService.getAll(name)

        assertEquals(fakeCustomers, customers)
        verify(exactly = 0) { customerRepository.findAll() }
        verify(exactly = 1) { customerRepository.findByNameContaining(name) }
    }

    @Test
    fun `should create customer and encrypt password`() {
        val initialPassword = Math.random().toString()
        val fakeCustomers = buildCustomer(password = initialPassword)
        val fakePassword = UUID.randomUUID().toString()
        val fakeCustomerEncrypted = fakeCustomers.copy(password = fakePassword)

        every { customerRepository.save(fakeCustomerEncrypted) } returns fakeCustomers
        every { bCrypt.encode(initialPassword) } returns fakePassword

        customerService.create(fakeCustomers)

        verify(exactly = 1) { customerRepository.save(fakeCustomerEncrypted) }
        verify(exactly = 1) { bCrypt.encode(initialPassword) }
    }

    @Test
    fun `should return customer when ID is informed`() {
        val id = Math.random().toInt()
        val fakeCustomers = buildCustomer()

        every { customerRepository.findById(id) } returns Optional.of(fakeCustomers)

        val customers = customerService.getById(id)

        assertEquals(fakeCustomers, customers)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    @Test
    fun `should throw not found when find by id`() {
        val id = Math.random().toInt()

        every { customerRepository.findById(id) } returns Optional.empty()

        val error = assertThrows<NotFoundException>{
            customerService.getById(id)
        }

        assertEquals("ML202", error.errorCode)
        assertEquals("Customer $id not exists", error.message)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    @Test
    fun `should update customer`() {
        val id = Math.random().toInt()
        val fakeCustomers = buildCustomer(id = id)

        every { customerRepository.existsById(id) } returns true
        every { customerRepository.save(fakeCustomers) } returns fakeCustomers

        customerService.update(fakeCustomers)

        verify(exactly = 1) { customerRepository.save(fakeCustomers) }
        verify(exactly = 1) { customerRepository.existsById(id) }
    }

    @Test
    fun `should throw not found when update customer`() {
        val id = Math.random().toInt()
        val fakeCustomers = buildCustomer(id = id)

        every { customerRepository.existsById(id) } returns false
        every { customerRepository.save(fakeCustomers) } returns fakeCustomers

        val error = assertThrows<NotFoundException>{
            customerService.update(fakeCustomers)
        }

        assertEquals("ML202", error.errorCode)
        assertEquals("Customer $id not exists", error.message)
        verify(exactly = 0) { customerRepository.save(any()) }
        verify(exactly = 1) { customerRepository.existsById(id) }
    }

    @Test
    fun `should delete customer`() {
        val id = Math.random().toInt()
        val fakeCustomers = buildCustomer(id = id)
        val expectedCustomer = fakeCustomers.copy(status = CustomerStatus.INATIVO)

        every { customerService.getById(id) } returns fakeCustomers
        every { customerRepository.save(expectedCustomer) } returns expectedCustomer
        every { bookService.deleteByCustomer(fakeCustomers) } just runs

        customerService.delete(id)

        verify(exactly = 1) { customerService.getById(id) }
        verify(exactly = 1) { bookService.deleteByCustomer(fakeCustomers) }
        verify(exactly = 1) { customerRepository.save(expectedCustomer) }
    }

    @Test
    fun `should throw not found exception when delete customer`() {
        val id = Math.random().toInt()

        every { customerService.getById(id) } throws NotFoundException(Errors.ML202.message.format(id), Errors.ML202.code)

        val error = assertThrows<NotFoundException>{
            customerService.delete(id)
        }

        assertEquals("ML202", error.errorCode)
        assertEquals("Customer $id not exists", error.message)

        verify(exactly = 1) { customerService.getById(id) }
        verify(exactly = 0) { bookService.deleteByCustomer(any()) }
        verify(exactly = 0) { customerRepository.save(any()) }
    }

    @Test
    fun `should return true when email available`() {

        val email = "${UUID.randomUUID()}@email.com"

        every { customerRepository.existsByEmail(email) } returns false

        val emailAvailable = customerService.emailAvailable(email)

        assertTrue(emailAvailable)
        verify(exactly = 1) { customerRepository.existsByEmail(email) }
    }

    @Test
    fun `should return false when email unavailable`() {

        val email = "${UUID.randomUUID()}@email.com"

        every { customerRepository.existsByEmail(email) } returns true

        val emailAvailable = customerService.emailAvailable(email)

        assertFalse(emailAvailable)
        verify(exactly = 1) { customerRepository.existsByEmail(email) }
    }
}