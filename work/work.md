# 数据库

## 										 设备表

|   字段名   |   数据类型    |      备注      |
| :--------: | :-----------: | :------------: |
|  eqpt_id   |    int(11)    | 设备id（主键） |
| eqpt_name  | varchar(255)  |    设备名称    |
| eqpt_price | double(255,0) |    设备价格    |



## 										设备参数表

|      字段名      |   数据类型    |      备注      |
| :--------------: | :-----------: | :------------: |
|     eqpt_id      |    int(11)    | 设备id（外键） |
| eqpt_temperature | double(255,0) |      温度      |
|  eqpt_humidity   | double(255,0) |      湿度      |
|   create_date    |   datetime    |  数据产生时间  |

## 											用户表

|   字段名   |   数据类型   |       备注       |
| :--------: | :----------: | :--------------: |
|  user_id   | varchar(255) |      用户id      |
| user_name  | varchar(255) |      用户名      |
|  user_pwd  | varchar(255) | 密码（md5加密）  |
| user_phone | varchar(255) | 电话（电话登录） |
| user_email | varchar(255) |       邮箱       |
| user_role  | varchar(255) |       角色       |

## API 接口：

- 用户登录：http://329775f22d.qicp.vip/user/login
- 请求方式：POST
- 传入参数：

```json
{
    "userPhone":"admin",
    "userPwd":"1234"
}
```

- 输入参数：

```JSON
{
    "code": "SUCCESS",
    "message": "登陆成功！",
    "data": "9aba1053-40d5-420d-9024-b8c34990d6c9" //token 还没设置拦截
}
```

- 用户注册：http://329775f22d.qicp.vip/user/register
- 请求方式：POST
- 传入参数：

```JSON
{
    "userName":"admin",
    "userPwd":"1234",
    "userPhone":"admin",
    "userEmail":"text@qq.com"
}
```

- 输出参数：

```json
{
    "code": "SUCCESS",
    "message": "成功",
    "data": "注册成功"
}
```

- 获取所有设备数据：http://329775f22d.qicp.vip//eqpt/getEqptlist
- 请求方式：GET
- 输出参数：

```JSON
{
    "code": "SUCCESS",
    "message": "成功",
    "data": [
        {
            "eqptId": 1,
            "eqptName": "测试设备1",
            "eqptPrice": 999.0
        },
        {
            "eqptId": 2,
            "eqptName": "测试设备2",
            "eqptPrice": 888.0
        },
        {
            "eqptId": 3,
            "eqptName": "测试设备3",
            "eqptPrice": 777.0
        },
        {
            "eqptId": 4,
            "eqptName": "测试设备4",
            "eqptPrice": 6666.0
        }
    ]
}
```

- 获取指定设备数据（只有设备1）：http://329775f22d.qicp.vip/eqpt-data/getEqptTemp
- 请求方式：POST
- 输入参数：

```Json
{
    "eqptId":1,
    "startDate":"2021-05-04 16:07:45",
    "endDate":"2021-05-05 16:08:32"
}
```

- 输出参数：

- ```JSON
  {
      "eqptId":1,
      "startDate":"2021-05-04 16:07:45", //可以为NULL
      "endDate":"2021-05-05 16:08:32"
  }
  ```

  