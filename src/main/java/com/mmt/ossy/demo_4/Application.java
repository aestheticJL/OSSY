package com.mmt.ossy.demo_4;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("1，创建表格");
            System.out.println("2，录入学生成绩");
            System.out.println("3，查找成绩");
            System.out.println("4，修改成绩");
            System.out.println("5，显示所有的学生成绩");
            System.out.println("6，复制表格");
            System.out.println("0，退出程序");
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("输入表格名称");
                    String fileName = sc.next();
                    CURD.create(fileName);
                    break;
                case 2:
                    System.out.println("选择表格");
                    String fileName2 = sc.next();
                    System.out.println("输入学号");
                    int studentId = sc.nextInt();
                    System.out.println("输入班级");
                    String className = sc.next();
                    System.out.println("输入姓名");
                    String studentName = sc.next();
                    System.out.println("输入成绩");
                    int score = sc.nextInt();
                    CURD.insert(fileName2, studentId, className, studentName, score);
                    break;
                case 3:
                    System.out.println("选择表格");
                    String fileName3 = sc.next();
                    System.out.println("输入学号");
                    int studentId3 = sc.nextInt();
                    CURD.select(fileName3, studentId3);
                    break;
                case 4:
                    System.out.println("选择表格");
                    String fileName4 = sc.next();
                    System.out.println("输入学号");
                    int studentId4 = sc.nextInt();
                    System.out.println("输入新成绩");
                    int newScore = sc.nextInt();
                    CURD.update(fileName4, studentId4, newScore);
                    break;
                case 5:
                    System.out.println("选择表格");
                    String fileName5 = sc.next();
                    CURD.read(fileName5);
                    break;
                case 6:
                    System.out.println("选择被复制表格");
                    String fileName6 = sc.next();
                    System.out.println("选择插入表格");
                    String copyFile = sc.next();
                    CURD.copy(fileName6, copyFile);
                    break;
                case 0:
                    return;
            }
        }
    }
}
