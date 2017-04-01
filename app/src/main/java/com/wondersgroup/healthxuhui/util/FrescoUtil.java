package com.wondersgroup.healthxuhui.util;

import android.content.Context;
import android.graphics.Bitmap;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestLoggingListener;

import java.util.HashSet;
import java.util.Set;

/**
 * fresco 图片加载框架util
 * Created by yang on 16/7/3.
 */
public class FrescoUtil {
    /**
     * 初始化,放在application 或者 setContentView 之前
     * @param context
     */
    public static void init(Context context){
        FLog.setMinimumLoggingLevel(FLog.VERBOSE);
        Set<RequestListener> listeners = new HashSet<>();
        listeners.add(new RequestLoggingListener());
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(context)
                .setRequestListeners(listeners)
                .setBitmapsConfig(Bitmap.Config.ARGB_8888)
                .build();
        Fresco.initialize(context, config);
    }
}
