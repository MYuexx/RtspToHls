# RtspToHls
#### 1、推流

接口地址：http://ip:port/start

接口类型：POST

接口参数：

```json
{
    "url":"rtsp://******",    //必填
    "name":"aaaa"     //新推流时不填，延时填返回的name字段，脚本10分钟自动销毁，建议9分钟延时一次
}
```

返回结果：

```json
{
    "code": 8200,
    "name": "ST2rjQG5S1",
    "message": "请求成功。",
    "url": "/hls/ST2rjQG5S1.m3u8"
}
```

结果使用说明：

拿到结果后播放地址是：http://ip:port + url

#### 2、停止

接口地址：http://ip:port/stop?name=aaa

接口类型：GET

参数说明：name取值为推流接口返回的name字段值


前置条件参考：https://blog.csdn.net/qq_20391065/article/details/115723208
