import React from "react";

export interface ContextInterface {
  isOpenActiveMenu: boolean;
  setIsOpenActiveMenu: React.Dispatch<boolean>;
  user: User,
  isAuth: boolean,
}

export interface User {
  name: string;
  surname: string;
  avatar: string,
  role: Role;
  email: string;
}

export enum Role {
  Headman = "Староста",
}