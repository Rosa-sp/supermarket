package com.qqw.supermarket.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Classname AccountDTO
 * @Description TODO()
 * @Date 2019/12/30 19:45
 * @Author by Alex
 */
@Data
@EqualsAndHashCode(callSuper = true)

public class AccountDTO extends Account {

   private List<Role> roles;
}
