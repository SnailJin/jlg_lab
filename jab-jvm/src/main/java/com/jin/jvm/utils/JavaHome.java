package com.jin.jvm.utils;

import java.io.File;


public class JavaHome {

    public final String path = System.getenv("JAVA_HOME")+File.separator +"bin"+File.separator;

}
