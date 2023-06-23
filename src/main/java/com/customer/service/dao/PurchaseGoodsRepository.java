package com.customer.service.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.service.model.PurchaseGoods;

public interface PurchaseGoodsRepository extends JpaRepository<PurchaseGoods,Integer>{

}
