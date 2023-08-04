package com;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class BornTest {
    public static void main(String[] args) {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();

        // 获取用户输入的出生日期
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入您的出生日期（yyyy-mm-dd）:");
        String inputDate = scanner.nextLine();

        // 解析用户输入的日期
        LocalDate birthDate = LocalDate.parse(inputDate);

        // 计算年龄
        Period age = Period.between(birthDate, currentDate);

        // 输出结果
        System.out.println("您已经活了："+ age.getYears() + "年" + age.getMonths() + "个月" + age.getDays() + "天");

        // 关闭Scanner
        scanner.close();
    }
}
