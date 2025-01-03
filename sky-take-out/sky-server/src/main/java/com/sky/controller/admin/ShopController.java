// @author Tjzlo
// @version 2024/11/4 17:17

package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺相关接口")
@Slf4j
@RequiredArgsConstructor
public class ShopController {

    public static final String SHOP_STATUS = "SHOP_STATUS";

    private final RedisTemplate<Object,Object> redisTemplate;

    /**
     * 设置店铺营业状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("设置店铺营业状态")
    public Result setStatus(@PathVariable Integer status) {
        log.info("设置店铺状态为：{}", status == 1 ? "营业中" : "打烊中");
        redisTemplate.opsForValue().set(SHOP_STATUS, status);
        log.info("{}",this.getStatus());
        return Result.success();
    }

    /**
     * 获取店铺营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取店铺营业状态")
    public Result<Integer> getStatus(){

        Integer shopStatus = (Integer) redisTemplate.opsForValue().get(SHOP_STATUS);

        if (shopStatus == null) {
            log.warn("店铺营业状态未找到");
            return Result.error("店铺营业状态未找到");
        }
        log.info("后台获取当前店铺营业状态为: {}",shopStatus == 1 ? "营业中..." : "打烊中...");
        return Result.success(shopStatus);
    }

}
