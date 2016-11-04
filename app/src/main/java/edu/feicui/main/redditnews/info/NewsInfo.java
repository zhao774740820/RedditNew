package edu.feicui.main.redditnews.info;

/**
 * Created by Administrator on 2016/10/28.
 */

public class NewsInfo {

        String summary;
        String icon;
        String stamp;
        String title;
        int nid;
        String link;
        int type;

        public NewsInfo() {
        }

        public NewsInfo(String summary, String icon, String stamp, String title, int nid, String link, int type) {
            this.summary = summary;
            this.icon = icon;
            this.stamp = stamp;
            this.title = title;
            this.nid = nid;
            this.link = link;
            this.type = type;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getStamp() {
            return stamp;
        }

        public void setStamp(String stamp) {
            this.stamp = stamp;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getNid() {
            return nid;
        }

        public void setNid(int nid) {
            this.nid = nid;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "News{" +
                    "summary='" + summary + '\'' +
                    ", icon='" + icon + '\'' +
                    ", stamp='" + stamp + '\'' +
                    ", title='" + title + '\'' +
                    ", nid=" + nid +
                    ", link='" + link + '\'' +
                    ", type=" + type +
                    '}';
        }
    }


