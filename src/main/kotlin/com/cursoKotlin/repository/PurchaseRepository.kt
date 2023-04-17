package com.cursoKotlin.repository

import com.cursoKotlin.model.PurchaseModel
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository : CrudRepository<PurchaseModel, Int>{

}
