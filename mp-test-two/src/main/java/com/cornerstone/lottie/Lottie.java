package com.cornerstone.lottie;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.*;

public class Lottie {


    public static void main(String[] args) throws IOException, ScriptException {
//        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
//        ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
//
//        String name = "Runoob";
//        Integer result = null;
//
//        try {
//            nashorn.eval("print('" + name + "')");
//            result = (Integer) nashorn.eval("10 + 2");
//
//        }catch(ScriptException e){
//            System.out.println("执行脚本错误: "+ e.getMessage());
//        }
//
//        System.out.println(result.toString());


        ScriptEngineManager m = new ScriptEngineManager();
//获取JavaScript执行引擎
        ScriptEngine engine = m.getEngineByName("nashorn");

//        /使用管道流，将输出流转为输入流
        PipedReader prd = new PipedReader();
        PipedWriter pwt = new PipedWriter(prd);
//设置执行结果内容的输出流
        engine.getContext().setWriter(pwt);
//js文件的路径
        String strFile = Thread.currentThread().getClass().getResource("/").getPath() + "/lottie.js";
        Reader reader = new FileReader(new File(strFile));
        engine.eval(reader);
        BufferedReader br = new BufferedReader(prd);
//开始读执行结果数据
        String str = null;
        while ((str = br.readLine()) != null && str.length() > 0) {
            System.out.println(str);
        }
        br.close();
        pwt.close();
        prd.close();
    }
}
