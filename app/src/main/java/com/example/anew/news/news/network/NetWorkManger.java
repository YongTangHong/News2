package com.example.anew.news.news.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2017/3/22.
 */

public class NetWorkManger {

    //创建一个全局的请求队列
    private static RequestQueue sQueue;

    private static ImageLoader sLoader;

    private static final int MAX_CACHE_SIZE = 5*1024 * 1024;
    //初始化全局队列
    public static void init(Context context) {
        sQueue = Volley.newRequestQueue(context);
        sLoader = new ImageLoader(sQueue,new newsImageCache(MAX_CACHE_SIZE) );
    }
    //发送网络请求
    public static void sendRequest(Request request) {
        sQueue.add(request);
    }
    private static class newsImageCache extends LruCache<String,Bitmap> implements ImageLoader.ImageCache {



        /**
         * @param maxSize for caches that do not override {@link #sizeOf}, this is
         *                the maximum number of entries in the cache. For all other caches,
         *                this is the maximum sum of the sizes of the entries in this cache.
         */
        public newsImageCache(int maxSize) {
            super(maxSize);


        }

        @Override
        protected int sizeOf(String key, Bitmap value) {


            return value.getByteCount();
        }


        @Override
        public Bitmap getBitmap(String url) {
            return get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            //将图片加入缓存

            put(url, bitmap);

        }
    }
    public static ImageLoader getImageLoader() {
        return sLoader;
    }
}
