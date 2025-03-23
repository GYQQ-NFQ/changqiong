// @author Tjzlo
// @version 2025/3/22 18:21

package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.admin.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@Slf4j
@RestController
@Api(tags = "数据统计相关接口")
@RequiredArgsConstructor
@RequestMapping("/admin/report")
public class ReportController {

    private final ReportService reportService;

    @RequestMapping("/turnoverStatistics")
    @ApiOperation("营业额统计")
    public Result<TurnoverReportVO> turnoverStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("营业额统计，开始日期：{}，结束日期：{}", begin, end);
        return Result.success(reportService.getTurnoverStatistics(begin, end));
    }

    @RequestMapping("/userStatistics")
    @ApiOperation("用户统计")
    public Result<UserReportVO> userStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("用户统计，开始日期：{}，结束日期：{}", begin, end);
        return Result.success(reportService.getUserStatistics(begin, end));
    }

    @RequestMapping("/ordersStatistics")
    @ApiOperation("订单统计")
    public Result<OrderReportVO> ordersStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("订单统计，开始日期：{}，结束日期：{}", begin, end);
        return Result.success(reportService.getOrderStatistics(begin, end));
    }

    @RequestMapping("/top10")
    @ApiOperation("top10")
    public Result<SalesTop10ReportVO> top10(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("top10，开始日期：{}，结束日期：{}", begin, end);
        return Result.success(reportService.getTop10(begin, end));
    }

    @RequestMapping("/export")
    @ApiOperation("根据id查询菜品或套餐")
    public void exportData(HttpServletResponse response) {
        log.info("导出数据，id");
        reportService.exportData(response);
    }
}
