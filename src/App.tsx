import React from "react";
import { BrowserRouter, Route, Routes, Link } from "react-router-dom";

import {
  Box,
  Drawer,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
} from "@mui/material";
import InboxIcon from "@mui/icons-material/Inbox";

import NavBar from "./components/NavBar/NavBar";
import { useStateContext } from "./contexts/ContextProvider";
import { ContextInterface } from "./contexts/types";
import logo from "./assets/logo.png";
import "./App.css";
import { Faq, MainPage, Students, Subjects, UploadFile } from "./pages";

const App = () => {
  const { isOpenActiveMenu, setIsOpenActiveMenu } =
    useStateContext() as ContextInterface;

  return (
    <div className="App">
      <BrowserRouter>
        <Drawer
          open={isOpenActiveMenu}
          onClose={() => setIsOpenActiveMenu(false)}
        >
          <Box>
            <List>
              <ListItem
                disablePadding
                component={Link}
                to="/"
                onClick={() => setIsOpenActiveMenu(false)}
                sx={{ textDecoration: "none" }}
              >
                <ListItemButton>
                  <ListItemIcon>
                    <img src={logo} alt={"logo"} />
                  </ListItemIcon>
                </ListItemButton>
              </ListItem>

              <ListItem
                disablePadding
                component={Link}
                to="/Subjects"
                onClick={() => setIsOpenActiveMenu(false)}
              >
                <ListItemButton>
                  <ListItemIcon>
                    <InboxIcon />
                  </ListItemIcon>
                  <ListItemText primary="Предметы" />
                </ListItemButton>
              </ListItem>

              <ListItem
                disablePadding
                component={Link}
                to="/Students"
                onClick={() => setIsOpenActiveMenu(false)}
              >
                <ListItemButton>
                  <ListItemIcon>
                    <InboxIcon />
                  </ListItemIcon>
                  <ListItemText primary="Студенты" />
                </ListItemButton>
              </ListItem>

              <ListItem
                disablePadding
                component={Link}
                to="/uploadFiles"
                onClick={() => setIsOpenActiveMenu(false)}
              >
                <ListItemButton>
                  <ListItemIcon>
                    <InboxIcon />
                  </ListItemIcon>
                  <ListItemText primary="Загрузка документов" />
                </ListItemButton>
              </ListItem>

              <ListItem
                disablePadding
                component={Link}
                to="/faq"
                onClick={() => setIsOpenActiveMenu(false)}
              >
                <ListItemButton>
                  <ListItemIcon>
                    <InboxIcon />
                  </ListItemIcon>
                  <ListItemText primary="Вопрос/ответ" />
                </ListItemButton>
              </ListItem>
            </List>
          </Box>
        </Drawer>
        <NavBar />
        <Routes>
          <Route path={"/"} element={<MainPage />} />
          <Route path={"/students"} element={<Students />} />
          <Route path={"/subjects"} element={<Subjects />} />
          <Route path={"/uploadFiles"} element={<UploadFile />} />
          <Route path={"/faq"} element={<Faq />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default App;
