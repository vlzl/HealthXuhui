package com.wondersgroup.healthxuhui.data.entity;

/**
 * okhttp 请求的包装类
 * Created by yangjinxi on 2016/6/22.
 */
public class HttpResponeEntity<T>{

    /**
     * message : 操作成功
     * localObject : [{"xh":1,"name":"徐汇区斜土街道社区卫生服务中心","address":"上海市徐汇区零陵北路2号","code":"200032","jc":"斜土","jingdu":121.47112,"weidu":31.20274,"telephone":"64042727","qxrk":3000,"yjyqy":4490},{"xh":2,"name":"徐汇区枫林街道社区卫生服务中心","address":"上海市徐汇区双峰路450号","code":"200030","jc":"枫林","jingdu":121.455775,"weidu":31.1864,"telephone":"64399271","qxrk":3100,"yjyqy":4420},{"xh":3,"name":"徐汇区徐家汇街道社区卫生服务中心","address":"上海市徐汇区广元西路349号","code":"200030","jc":"徐家汇","jingdu":121.438339,"weidu":31.200849,"telephone":"64479206","qxrk":3200,"yjyqy":4584},{"xh":4,"name":"徐汇区天平街道社区卫生服务中心","address":"上海市徐汇区太原路110号","code":"200031","jc":"天平","jingdu":121.461196,"weidu":31.213122,"telephone":"64319642","qxrk":3300,"yjyqy":2045},{"xh":5,"name":"徐汇区湖南街道社区卫生服务中心","address":"上海市徐汇区五原路281弄7号","code":"200031","jc":"湖南","jingdu":121.453115,"weidu":31.21842,"telephone":"34230101","qxrk":3400,"yjyqy":1162},{"xh":6,"name":"徐汇区龙华街道社区卫生服务中心","address":"上海市徐汇区龙华西路249号","code":"200232","jc":"龙华","jingdu":121.453629,"weidu":31.179556,"telephone":"64560088","qxrk":3500,"yjyqy":3451},{"xh":7,"name":"徐汇区田林街道社区卫生服务中心","address":"上海市徐汇区柳州路500号","code":"200233","jc":"田林","jingdu":121.429077,"weidu":31.179601,"telephone":"64751150","qxrk":3600,"yjyqy":4021},{"xh":8,"name":"徐汇区康健街道社区卫生服务中心","address":"上海市徐汇区江安路88号","code":"200233","jc":"康健","jingdu":121.42106,"weidu":31.154394,"telephone":"54181166","qxrk":3700,"yjyqy":4665},{"xh":9,"name":"徐汇区虹梅街道社区卫生服务中心","address":"上海市徐汇区虹梅路1501号","code":"200233","jc":"虹梅","jingdu":121.408371,"weidu":31.168594,"telephone":"54486140","qxrk":3800,"yjyqy":2370},{"xh":10,"name":"徐汇区漕河泾街道社区卫生服务中心","address":"上海市徐汇区宾阳路38号","code":"200237","jc":"漕河泾","jingdu":121.442979,"weidu":31.170085,"telephone":"54113581","qxrk":3900,"yjyqy":3645},{"xh":11,"name":"徐汇区长桥街道社区卫生服务中心","address":"上海市徐汇区龙川北路9号","code":"200231","jc":"长桥","jingdu":121.446787,"weidu":31.145535,"telephone":"64530038","qxrk":4000,"yjyqy":3922},{"xh":12,"name":"徐汇区凌云街道社区卫生服务中心","address":"上海市徐汇区凌云路99号","code":"200237","jc":"凌云","jingdu":121.428154,"weidu":31.138571,"telephone":"64774811","qxrk":4100,"yjyqy":3123},{"xh":13,"name":"徐汇区华泾街道社区卫生服务中心","address":"上海市徐汇区建华路180号","code":"200231","jc":"华泾","jingdu":121.450945,"weidu":31.127458,"telephone":"64962252","qxrk":4200,"yjyqy":3691},{"xh":14,"name":"徐汇区疾病预防控制中心","address":"上海市徐汇区永川路50号","code":"200237","jc":"区疾控","jingdu":121.447195,"weidu":31.158511,"telephone":"54012700","qxrk":46800,"yjyqy":45589},{"xh":15,"name":"上海市疾病预防控制中心","address":"上海市中山西路1380号","code":"200336","jc":"市疾控","jingdu":121.424244,"weidu":31.199372,"telephone":"62758710"}]
     * resultCode : 200237
     */

    private String message;
    private int resultCode;
    private T localObject;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public T getLocalObject() {
        return localObject;
    }

    public void setLocalObject(T localObject) {
        this.localObject = localObject;
    }
}
