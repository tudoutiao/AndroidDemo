package com.example.app.refresh;

import android.text.TextUtils;

import java.util.ArrayList;

import cn.jzvd.VideoInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * 封装资讯等数据(因资讯,段子,图片,谣言的数据结构一样,所以只用这一个实体封装)
 */

@Setter
@Getter
public class Link{
    /*大图前置优于多图*/
    public static int DEFT_TYPE = 0; //默认模式 小配图 点击后进入原连接或者内容页
    public static int BIG_IMG_TYPE = 1; //配图前置图片模式 点击后进入原连接或者内容页
    public static int VIDEO_TYPE = 2; //mp4(视频类型) 点击后进入视频内容页
    public static int GIF_TYPE = 3;//gif(视频类型) 点击后进入视频内容页面
    public static int MP4_FRONT = 4;//(视频前置类型)  点击后进入原链接
    public static int GIF_FRONT = 5;//(gif视频前置类型) 点击后进入原链接

    private static final String TAG = "Link";
    protected int id;
    protected String title = "";

    public String getStitle() {
        return stitle;
    }

    protected String stitle = "";
    protected String summary = "";
    protected String url;

    protected String originalUrl;
    protected String img_url;
    protected String original_img_url;
    protected int ups;
    protected boolean has_uped;
    protected int comments_count;
    // action_time 收藏,发布,推荐,评论的时间
    protected long action_time;
    protected long created_time;
    protected String score;
    protected int action;
    protected boolean has_saved;
    protected boolean has_read;
    protected boolean picFront;

    private ScrollTag scrollTag;
    private int subject_id;
    private boolean is_break;
    private boolean is_top;
    private long time_into_pool;
    private boolean noComments;//被封禁
    //video
    private String videoUrl;
    private long videoSize;//byte
    private int videoDuration;//mm
    private int showType;// * 0 or null 关闭状态;1 前置配图 ; 2 mp4(视频类型) ;3 gif(视频类型)
    private String videoSourceType;//视频类型 现在有 mp4和gif
    private String videoImgUrl;
    private int videoWidth;
    private int videoHeight;
    /*** true:跟帖开放时间已过*/
    private boolean commentTimeout;
    private VideoInfo videoItemInfo;
    /*是否多图*/
    private boolean multigraph;
    /*多图地址*/
    private ArrayList<String> multigraphList = new ArrayList<>();
    /*记录视频点击事件上传*/
    private String clickType;
    /**
     * 是否允许图片评论
     */
    private boolean commentHavePicture;
    /**
     * 是否显示头列表
     */
    private boolean isShowOperation;
    /**
     * 是否首位显示上次浏览
     */
    private boolean isFirst;
    /**
     * 板块id
     */
    private String sectionId;
    /**
     * 板块下权限  0：用户  10：管理员   20：版主
     */
    private int sectionManager;
    /**
     * 板块名
     */
    private String sectionName;
    /**
     * 板块下发布时间
     */
    private long lastComment;
    /**
     * 子版块帖子 加精热标签
     */
    private String tag;
    /**
     * 相关阅读 是否显示
     */
    private boolean relateRead;

    /**
     * 是否点击展开显示最近阅读（下拉刷新关闭）
     */
    private boolean isShowRelated;

    private int relateIndex = -1;

    private boolean isRecomment;
    private String topicId;
    private String topicName;

    private String body;


    /**
     * 是否需要加载更多信息
     * 如果true 需要客户端请求帖子详情获取详细信息
     */
    private boolean moreInfo = false;

    /**
     * 话题推荐title
     */
    private String sectionLinkUrlTitle;

    /**
     * 是否显示话题推荐
     */
    private boolean sectionRecommend;

    /**
     * 话题推荐
     */
    private ArrayList<Link> sectionRecommendList;
    /**
     * 话题头像
     */
    private String sectionImgUrl;
    /**
     * 话题下帖子数量
     */
    private Long sectionLinkCount;


    public Link() {
        super();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Link) {
            if (this.id == ((Link) obj).getId()) {
                return true;
            }
        }
        return false;
    }


    public String getUrl() {
        if (TextUtils.isEmpty(url))
            return url;
        if (url.startsWith("HTTPS")) {
            url = url.replaceFirst("HTTPS", "https");
        } else if (url.startsWith("HTTP")) {
            url = url.replaceFirst("HTTP", "http");
        }
        return url;
    }


    public static final int INIT = 0;
    public static final int RFRESH = 1;
    public static final int RFRESHOVER = 2;
    public static final int HISTORY = 3;

    public static class ScrollTag {
        int type = INIT;
        int count;
        int index;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
