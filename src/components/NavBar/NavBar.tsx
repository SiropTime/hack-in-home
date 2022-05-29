import React from "react";

import {
  AppBar,
  Avatar,
  Box,
  Divider,
  IconButton,
  Menu,
  MenuItem,
  Toolbar,
} from "@mui/material";
import MenuIcon from "@mui/icons-material/Menu";
import { AccountCircle } from "@mui/icons-material";

import { useStateContext } from "../../contexts/ContextProvider";
import { ContextInterface } from "../../contexts/types";

const NavBar = () => {
  const { isOpenActiveMenu, setIsOpenActiveMenu, user } =
    useStateContext() as ContextInterface;

  const { name, surname, avatar } = user;

  const [anchorEl, setAnchorEl] = React.useState<HTMLElement | null>(null);

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar color="primary" elevation={0} position={"static"}>
        <Toolbar sx={{ justifyContent: "space-between" }}>
          <IconButton
            size="medium"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
            onClick={() => setIsOpenActiveMenu(!isOpenActiveMenu)}
          >
            <MenuIcon />
          </IconButton>
          <IconButton
            size="medium"
            aria-label="account of current user"
            aria-controls="menu-appbar"
            aria-haspopup="true"
            color="inherit"
            onClick={(e) => setAnchorEl(e.currentTarget)}
          >
            <AccountCircle />
          </IconButton>
          <Menu
            id="menu-appbar"
            anchorEl={anchorEl}
            anchorOrigin={{
              vertical: "top",
              horizontal: "right",
            }}
            keepMounted
            transformOrigin={{
              vertical: "top",
              horizontal: "right",
            }}
            open={Boolean(anchorEl)}
            onClose={() => setAnchorEl(null)}
          >
            <MenuItem>
              <Avatar sx={{ mr: 3 }} src={avatar} /> {`${surname} ${name}`}
            </MenuItem>
            <Divider />
            <MenuItem onClick={() => setAnchorEl(null)}>Профиль</MenuItem>
            <MenuItem onClick={() => setAnchorEl(null)}>Настройки</MenuItem>
            <MenuItem onClick={() => setAnchorEl(null)}>Выйти</MenuItem>
          </Menu>
        </Toolbar>
      </AppBar>
    </Box>
  );
};

export default NavBar;
