package com.hex.springcloudorder.repository;

import com.hex.springcloudorder.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: hexuan
 * Date: 2019/9/18
 * Time: 11:23 AM
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
