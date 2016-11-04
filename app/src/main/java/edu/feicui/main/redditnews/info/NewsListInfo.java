package edu.feicui.main.redditnews.info;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/28.
 */

public class NewsListInfo {

        String message;
        int status;
        ArrayList<NewsInfo> data;

        public NewsListInfo() {
        }

        public NewsListInfo(String message, int status, ArrayList<NewsInfo> data) {
            this.message = message;
            this.status = status;
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public ArrayList<NewsInfo> getData() {
            return data;
        }

        public void setData(ArrayList<NewsInfo> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Xinwen{" +
                    "message='" + message + '\'' +
                    ", status=" + status +
                    ", data=" + data +
                    '}';
        }
    }

