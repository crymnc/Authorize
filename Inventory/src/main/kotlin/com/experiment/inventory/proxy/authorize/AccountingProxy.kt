package com.experiment.inventory.proxy.authorize

import org.springframework.cloud.openfeign.FeignClient

@FeignClient(name = "accounting")
interface AccountingProxy {
}