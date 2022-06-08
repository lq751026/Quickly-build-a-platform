package com.object.statuscode;

/**
 * 登录状态
 */
public enum  TUserStatusCode {
      enable(1),freeze(0);
      public int stats=0;
      TUserStatusCode(int stats){
          this.stats=stats;
     }
}
