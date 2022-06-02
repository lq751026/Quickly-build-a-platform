package com.object.utils;

import java.lang.management.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SysUtile {
    private static NumberFormat fmtI = new DecimalFormat("###,###", new DecimalFormatSymbols(Locale.ENGLISH));
    private static NumberFormat fmtD = new DecimalFormat("###,##0.000", new DecimalFormatSymbols(Locale.ENGLISH));

    private static final int Kb = 1024;

    public static void main(String[] args) {
        Map<String, Object> map = new SysUtile().printSummary();
        for (Map.Entry<String, Object> data : map.entrySet()) {
            System.out.println(data.getKey()+":"+data.getValue());
        }
    }


    public static Map<String,Object> printSummary() {
        Map<String,Object> map=new HashMap<>();
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        //获取JVM的启动时间，版本及名称，当前进程ID,环境变量等
      //   System.out.printf("jvmName:%s %s %s%n", runtime.getVmName(), "version", runtime.getVmVersion());//jvm 名称
      //   System.out.printf("jvmJavaVer:%s%n", System.getProperty("java.version")); //Java版本号
      //   System.out.printf("jvmUptime:%s%n", toDuration(runtime.getUptime()));//jvm正常运行时间
      //   System.out.printf("processId:%S%n", runtime.getName().split("@")[0]);//processId:11844
        map.put("jvmName", runtime.getVmName());
        map.put("jvmJavaVer", System.getProperty("java.version"));
        map.put("jvmUptime", toDuration(runtime.getUptime()));
        map.put("processId", runtime.getName().split("@")[0]);


        //System.out.println("---------------------------------------------");
        //获取JVM内存使用状况，包括堆内存和非堆内存
        MemoryMXBean mem = ManagementFactory.getMemoryMXBean();
      //   System.out.printf("heapCurrentUsage:%d mb %n", mem.getHeapMemoryUsage().getUsed()  / Kb / Kb);//堆当前使用
      //   System.out.printf("heapMax:%d mb %n", mem.getHeapMemoryUsage().getMax()  / Kb / Kb);//堆最大值
      //   System.out.printf("heapCommitted:%d mb%n", mem.getHeapMemoryUsage().getCommitted() / Kb / Kb);//堆已提交
        map.put("heapCurrentUsage", mem.getHeapMemoryUsage().getUsed()  / Kb / Kb);
        map.put("heapMax", mem.getHeapMemoryUsage().getMax()  / Kb / Kb);
        map.put("heapCommitted", mem.getHeapMemoryUsage().getCommitted() / Kb / Kb);

        //System.out.println("---------------------------------------------");
        //操作系统及硬件信息：系统名称、版本，CPU内核，机器总内存、可用内存、可用内存占比
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
      //   System.out.printf("osName:%s%n", os.getName());//系统名称
      //   System.out.printf("osVersion: %s%n", os.getVersion());//osVersion
      //   System.out.printf("osAvailableProcessors:%s%n", os.getAvailableProcessors());//操作系统可用处理器
        map.put("osName",  os.getName());
        map.put("osVersion",os.getVersion());
        map.put("osAvailableProcessors", os.getAvailableProcessors());

       // System.out.println("---------------------------------------------");
        //虚拟机线程系统的管理,可以获取某个线程信息(阻塞时间，次数，堆栈信息等)
        ThreadMXBean threads = ManagementFactory.getThreadMXBean();
      //   System.out.printf("threadsLiveCount:%d%n", threads.getThreadCount());//线程实时计数
      //   System.out.printf("threadsDaemonCount:%d%n", threads.getDaemonThreadCount());//线程守护程序计数
      //   System.out.printf("threadsPeakCount:%d%n", threads.getPeakThreadCount());//线程峰值计数
      //   System.out.printf("threadsTotalCount:%d%n", threads.getTotalStartedThreadCount());//线程总数
      //   System.out.printf("AllIds:%s%n", toString(threads.getAllThreadIds()));//所有 ID
        map.put("threadsLiveCount",  threads.getThreadCount());
        map.put("threadsDaemonCount",threads.getDaemonThreadCount());
        map.put("threadsPeakCount", threads.getPeakThreadCount());
        map.put("threadsTotalCount",  threads.getTotalStartedThreadCount());
        map.put("AllIds",toString(threads.getAllThreadIds()));

      //  System.out.println("---------------------------------------------");
        //Java虚拟机类加载系统的管理接口。
        ClassLoadingMXBean cl = ManagementFactory.getClassLoadingMXBean();
      //   System.out.printf("clsCurrLoaded:%s%n", cl.getLoadedClassCount());//获取加载的类计数
      //   System.out.printf("clsLoaded:%s%n", cl.getTotalLoadedClassCount());//获取总加载类计数
      //   System.out.printf("clsUnloaded:%s%n", cl.getUnloadedClassCount());//获取卸载的类计数
        map.put("clsCurrLoaded",  cl.getLoadedClassCount());//获取加载的类计数
        map.put("clsLoaded",cl.getTotalLoadedClassCount());//获取总加载类计数
        map.put("clsUnloaded", cl.getUnloadedClassCount());//获取卸载的类计数
    return map;
    }

    private static String printSizeInKb(double size) {
        return fmtI.format((long) (size / 1024)) + " kbytes";
    }

    private static String toDuration(double uptime) {
        uptime /= 1000;
        if (uptime < 60) {
            return fmtD.format(uptime) + " seconds";
        }
        uptime /= 60;
        if (uptime < 60) {
            long minutes = (long) uptime;
            String s = fmtI.format(minutes) + (minutes > 1 ? " minutes" : " minute");
            return s;
        }
        uptime /= 60;
        if (uptime < 24) {
            long hours = (long) uptime;
            long minutes = (long) ((uptime - hours) * 60);
            String s = fmtI.format(hours) + (hours > 1 ? " hours" : " hour");
            if (minutes != 0) {
                s += " " + fmtI.format(minutes) + (minutes > 1 ? " minutes" : " minute");
            }
            return s;
        }
        uptime /= 24;
        long days = (long) uptime;
        long hours = (long) ((uptime - days) * 24);
        String s = fmtI.format(days) + (days > 1 ? " days" : " day");
        if (hours != 0) {
            s += " " + fmtI.format(hours) + (hours > 1 ? " hours" : " hour");
        }
        return s;
    }

    private static String toString(long[] collection){
        if(collection.length > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for(long id : collection) {
                stringBuilder.append(id).append(",");
            }
            return stringBuilder.substring(0, stringBuilder.length() -1);
        }
        return null;
    }

}
