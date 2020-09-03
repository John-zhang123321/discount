package com.jkb.common.constants;

public enum RespCodeConstants {

  EX_OTHER_CODE(500, "系统异常"),
  TOKEN_IS_NOT_VALID(100001, "token无效"),
  CHECK_ACCOUNT_NOTEXIST_ERROR(100002, "账户不存在"),
  CHECK_ACCOUNT_PASSWORD_ERROR(100003, "密码错误"),
  CHECK_ACCUNT_EXIST_ERROR(10005,"账号已存在"),
  CHECK_USER_ID_EXIST_ERROR(10006,"用户数据已存在"),

  ADD_USERS_ERROR(20001, "添加失败"),
  DEL_USERS_ERROR(20002, "删除失败"),
  UPDATE_USERS_ERROR(20003, "更新失败"),

  DEFAULT_ERROR(200, "");

    private int code;
    private String msg;

  private RespCodeConstants(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
