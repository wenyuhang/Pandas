package com.example.pandas.model.datebean.homebean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Nicky on 2017/7/15.
 */

public class InteractiveInfoBean implements Parcelable {

    private List<InteractiveBean> interactive;

    protected InteractiveInfoBean(Parcel in) {
    }

    public static final Creator<InteractiveInfoBean> CREATOR = new Creator<InteractiveInfoBean>() {
        @Override
        public InteractiveInfoBean createFromParcel(Parcel in) {
            return new InteractiveInfoBean(in);
        }

        @Override
        public InteractiveInfoBean[] newArray(int size) {
            return new InteractiveInfoBean[size];
        }
    };

    public List<InteractiveBean> getInteractive() {
        return interactive;
    }

    public void setInteractive(List<InteractiveBean> interactive) {
        this.interactive = interactive;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static class InteractiveBean implements Parcelable {
        /**
         * image : http://p1.img.cctvpic.com/photoAlbum/page/performance/img/2017/6/5/1496626374961_922.jpg
         * title : 二十四节气——芒种
         * url : http://webapp.cctv.com/h5/travel/U80531QU7SY7.html
         * order : 1
         */

        private String image;
        private String title;
        private String url;
        private String order;

        protected InteractiveBean(Parcel in) {
            image = in.readString();
            title = in.readString();
            url = in.readString();
            order = in.readString();
        }

        public static final Creator<InteractiveBean> CREATOR = new Creator<InteractiveBean>() {
            @Override
            public InteractiveBean createFromParcel(Parcel in) {
                return new InteractiveBean(in);
            }

            @Override
            public InteractiveBean[] newArray(int size) {
                return new InteractiveBean[size];
            }
        };

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(image);
            dest.writeString(title);
            dest.writeString(url);
            dest.writeString(order);
        }
    }
}
