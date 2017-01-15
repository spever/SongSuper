package com.tencent.qcloud.suixinbo.presenters;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tencent.qcloud.suixinbo.model.AwardsCups;
import com.tencent.qcloud.suixinbo.model.LiveInfoJson;
import com.tencent.qcloud.suixinbo.model.LoginedProfileData;
import com.tencent.qcloud.suixinbo.model.MySelfInfo;
import com.tencent.qcloud.suixinbo.model.ProfileData;
import com.tencent.qcloud.suixinbo.utils.JsonUtil;
import com.tencent.qcloud.suixinbo.utils.LogUtil;
import com.tencent.qcloud.suixinbo.utils.SxbLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * 网络请求类
 */
public class OKhttpHelper {
    private static final String TAG = OKhttpHelper.class.getSimpleName();
    private static OKhttpHelper instance = null;
        public static final String GET_MYROOMID = "http://182.254.234.225/sxb/index.php?svc=user_av_room&cmd=get";
//    public static final String GET_MYROOMID = "http://182.254.234.225/sxb/index.php?svc=user_av_room&cmd=get";
    public static final String NEW_ROOM_INFO = "http://182.254.234.225/sxb/index.php?svc=live&cmd=start";
    public static final String STOP_ROOM = "http://182.254.234.225/sxb/index.php?svc=live&cmd=end";
    public static final String GET_LIVELIST = "http://182.254.234.225/sxb/index.php?svc=live&cmd=list";
    public static final String SEND_HEARTBEAT = "http://182.254.234.225/sxb/index.php?svc=live&cmd=host_heartbeat";
    public static final String GET_COS_SIG = "http://182.254.234.225/sxb/index.php?svc=cos&cmd=get_sign";

    public static final String GET_PROFILE = "http://182.254.234.225/oapi/ChUserCenter/getUserCups";
    public static final String GET_PROFILE_DATA = "http://182.254.234.225/oapi/ChUserCenter/user";
    public static final String LOGIN_OUR_SERVER = "http://182.254.234.225/oapi/ChUserCenter/txlogin";
    public static final String FOCUS_FANS = "http://182.254.234.225/oapi/ChUserCenter/followed";

    public static OKhttpHelper getInstance() {
        if (instance == null) {
            instance = new OKhttpHelper();
        }
        return instance;
    }


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .build();


    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            return "";
        }
    }

    /**
     * 同步Server 新创建房间信息
     */
    public int notifyServerNewLiveInfo(JSONObject reg) {
        try {

            String res = post(NEW_ROOM_INFO, reg.toString());
            SxbLog.i(TAG, "notifyServer live start  liveinfo: " + res);
            JSONTokener jsonParser = new JSONTokener(res);
            JSONObject response = (JSONObject) jsonParser.nextValue();
            SxbLog.i(TAG, "notifyServerNewLiveInfo: " + response);
            int code = response.getInt("errorCode");
            if (code == 0) {
                return code;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 同步Server 关闭房间信息
     */
    public LiveInfoJson notifyServerLiveStop(String id) {
        try {
            JSONObject stopLive = new JSONObject();
            stopLive.put("uid", id);
            stopLive.put("watchCount", 1000);
            stopLive.put("admireCount", 0);
            stopLive.put("timeSpan", 200);
            String json = stopLive.toString();
            String res = post(STOP_ROOM, json);
            SxbLog.i(TAG, "notifyServer live stop  liveinfo: " + res);
            JSONTokener jsonParser = new JSONTokener(res);
            JSONObject response = (JSONObject) jsonParser.nextValue();

            int code = response.getInt("errorCode");
            if (code == 0) {
                JSONObject data = response.getJSONObject("data");
                JSONObject record = data.getJSONObject("record");
                String recordS = record.toString();
                Gson gson = new GsonBuilder().create();
                LiveInfoJson result = gson.fromJson(recordS, LiveInfoJson.class);
                return result;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取自己的房间
     */
    public void getMyRoomId(final Context context) {
        try {
            JSONObject myId = new JSONObject();
            myId.put("uid", MySelfInfo.getInstance().getId());
            String response = OKhttpHelper.getInstance().post(GET_MYROOMID, myId.toString());
            JSONTokener jsonParser = new JSONTokener(response);
            JSONObject reg_response = (JSONObject) jsonParser.nextValue();
            int ret = reg_response.getInt("errorCode");
            if (ret == 0) {
                JSONObject data = reg_response.getJSONObject("data");
                int id = data.getInt("id");
//                int id = data.getInt("avRoomId");
                SxbLog.i(TAG, "getMyRoomId " + id);
                MySelfInfo.getInstance().setMyRoomNum(id);
                MySelfInfo.getInstance().writeToCache(context.getApplicationContext());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取直播列表
     *
     * @param page     页数
     * @param pagesize 每页个数
     * @return 返回直播列表
     */
    public ArrayList<LiveInfoJson> getLiveList(int page, int pagesize) {
        try {
            JSONObject req = new JSONObject();
            req.put("pageIndex", page);
            req.put("pageSize", pagesize);
            String response = OKhttpHelper.getInstance().post(GET_LIVELIST, req.toString());
            LogUtil.e("======", response);
            SxbLog.i(TAG, "getLiveList " + response.toString());
            JSONTokener jsonParser = new JSONTokener(response);
            JSONObject reg_response = (JSONObject) jsonParser.nextValue();
            int ret = reg_response.getInt("errorCode");
            if (ret == 0) {
                JSONObject data = reg_response.getJSONObject("data");
                LogUtil.e("======", data.toString());
                JSONArray record = data.getJSONArray("recordList");
                Type listType = new TypeToken<ArrayList<LiveInfoJson>>() {
                }.getType();
                ArrayList<LiveInfoJson> result = new Gson().fromJson(record.toString(), listType);
                return result;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    public void sendHeartBeat(String userid, int watchCount, int admireCount, int timeSpan) {
        try {
            JSONObject req = new JSONObject();
            req.put("uid", userid);
            req.put("watchCount", watchCount);
            req.put("admireCount", admireCount);
            req.put("timeSpan", timeSpan);
            String response = OKhttpHelper.getInstance().post(SEND_HEARTBEAT, req.toString());

            SxbLog.i(TAG, "sendHeartBeat " + response.toString());
            JSONTokener jsonParser = new JSONTokener(response);
            JSONObject reg_response = (JSONObject) jsonParser.nextValue();
            int ret = reg_response.getInt("errorCode");
            if (ret == 0) {
                SxbLog.i(TAG, "sendHeartBeat is Ok");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCosSig() {
        try {
            String response = OKhttpHelper.getInstance().post(GET_COS_SIG, "");

//            SxbLog.i(TAG, "getCosSig " + response.toString());
            JSONTokener jsonParser = new JSONTokener(response);
            JSONObject reg_response = (JSONObject) jsonParser.nextValue();
            int ret = reg_response.getInt("errorCode");
            if (ret == 0) {
                JSONObject data = reg_response.getJSONObject("data");
                String sign = data.getString("sign");
                return sign;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 个人中心奖杯
     * @param uid
     * @param page
     * @param perpage
     * @return
     */
    public List<AwardsCups> getProfileCenter(String uid, int page, int perpage) {
        JSONObject req = new JSONObject();
        try {
            req.put("user_id", uid);
            req.put("page", page);
            req.put("perpage", perpage);
            String response = OKhttpHelper.getInstance().post(GET_PROFILE, req.toString());
            JSONTokener jsonParser = new JSONTokener(response);
            JSONObject reg_response = (JSONObject) jsonParser.nextValue();
            JSONObject result = reg_response.getJSONObject("result");
            String code = result.getString("code");
            if (code.equals("200")) {
                JSONObject body = reg_response.getJSONObject("body");
                JSONArray cups = body.getJSONArray("cups");
                Type listType = new TypeToken<List<AwardsCups>>() {
                }.getType();
                List<AwardsCups> list_cups = (List<AwardsCups>) JsonUtil.parseJsonToList(cups.toString(), new TypeToken<List<AwardsCups>>() {
                }.getType());
                return list_cups;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 个人中心资料卡
     * @param uid
     * @return
     */
    public ProfileData GetProfileData(String uid) {
        JSONObject req = new JSONObject();
        try {
            req.put("user_id", uid);
            String response = OKhttpHelper.getInstance().post(GET_PROFILE_DATA, req.toString());
            JSONTokener jsonParser = new JSONTokener(response);
            JSONObject reg_response = (JSONObject) jsonParser.nextValue();
            JSONObject result = reg_response.getJSONObject("result");
            String code = result.getString("code");
            if (code.equals("200")) {
                JSONObject body = reg_response.getJSONObject("body");
                ProfileData data = JsonUtil.parseJsonToBean(body.toString(), ProfileData.class);
                return data;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 发送相关信息到自己服务区，获取更多相关信息
     * @param id
     * @param sign
     * @param nick_name
     * @param avatar
     * @return
     */
    public LoginedProfileData SendOurServerGetMoreInfo(String id, String sign, String nick_name, String avatar){
        JSONObject req = new JSONObject();
        try {
            req.put("id",id);
            req.put("sign",sign);
            req.put("nick_name",nick_name);
            req.put("avatar",avatar);
            String response = OKhttpHelper.getInstance().post(LOGIN_OUR_SERVER,req.toString());
            JSONTokener jsonParser = new JSONTokener(response);
            JSONObject reg_response = (JSONObject) jsonParser.nextValue();
            JSONObject result =  reg_response.getJSONObject("result");
            String code = result.getString("code");
            if ("200".equals(code)){
                JSONObject body = reg_response.getJSONObject("body");
                LoginedProfileData data = JsonUtil.parseJsonToBean(body.toString(), LoginedProfileData.class);
                return data;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void FollowFans(){

    }
}
