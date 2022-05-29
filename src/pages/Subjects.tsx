import React, { useEffect, useState } from "react";
import {
  Avatar,
  Box,
  Button,
  CircularProgress, Link,
  List,
  ListItem,
  ListItemAvatar,
  ListItemText,
  Modal,
  TextField,
  Typography,
} from "@mui/material";
import ImageIcon from "@mui/icons-material/Image";
import WorkIcon from "@mui/icons-material/Work";
import BeachAccessIcon from "@mui/icons-material/BeachAccess";
import ErrorMessage from "../components/ErrorMessage/ErrorMessage";
import useAdminService from "../service/useAdminService";

const style = {
  display: "flex",
  flexDirection: "column",
  position: "absolute",
  top: "30%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: '30%',
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

const Subject = () => {
  const { getSubjects, loading, error } = useAdminService();

  const [subjects, setSubjects] = useState<any>([]);
  const [isOpenModal, setIsOpenModal] = useState(false);
  const [activeElement, setActiveElement] = useState<any>({});
  useEffect(() => {
    updateSubjects();
  }, []);

  const updateSubjects = () => {
    getSubjects().then(onSubjectsLoaded);
  };

  const onSubjectsLoaded = (subjects: any) => {
    setSubjects(subjects);
  };
  return (
    <>
      <Modal
        open={isOpenModal}
        onClose={() => setIsOpenModal(false)}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography
            id="modal-modal-title"
            variant="h6"
            component="h2"
            sx={{ marginBottom: 2 }}
          >
            Информация о предмете {activeElement?.name}
          </Typography>
          <Typography
              id="modal-modal-teacher"
              variant="subtitle1"
              sx={{ marginBottom: 1 }}
          >
            Преподователь: {activeElement?.teacherId?.fullName || 'Нет информации о преподователе'}
          </Typography>
          <Typography
              id="modal-modal-phone"
              variant="subtitle1"
              sx={{ marginBottom: 1 }}
          >
            {activeElement?.teacherId?.phone ? `Номер телефона: ${activeElement?.teacherId?.phone}` : null}
          </Typography>
          <Link href={activeElement.url || '#'}>{activeElement.url ? 'Ссылка на предмет' : 'Нет ссылки на предмет'}</Link>
        </Box>
      </Modal>
      <Typography
        component={"h2"}
        gutterBottom
        align={"left"}
        color={"white"}
        sx={{ ml: 10, fontWeight: 700, position: "absolute", top: "20px" }}
      >
        Предметы
      </Typography>
      <List>
        {loading ? <CircularProgress /> : null}
        {error ? <ErrorMessage /> : null}
        {subjects
          ? subjects.map((el: any) => (
                <ListItem key={el.id} divider={true} onClick={() => setActiveElement(el)}>
                  <ListItemAvatar>
                    <Avatar>
                      <WorkIcon />
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText
                    onClick={() => setIsOpenModal(true)}
                    primary={el.name}
                    secondary={`Модуль: ${
                      el.moduleStartDate
                        ? el.moduleStartDate.slice(0, -19)
                        : "нет"
                    } / ${
                      el.moduleStartDate
                        ? el.moduleStartDate.slice(0, -19)
                        : "информации"
                    }`}
                    sx={{ cursor: "pointer" }}
                  />
                </ListItem>
            ))
          : null}
      </List>
    </>
  );
};

export default Subject;
