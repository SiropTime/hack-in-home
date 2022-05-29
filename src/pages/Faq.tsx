import React, { Dispatch, useEffect, useReducer, useState } from "react";
import {
  Box,
  Button,
  CircularProgress,
  IconButton,
  List,
  ListItem,
  ListItemText,
  Modal,
  TextField,
  Typography,
} from "@mui/material";
import DeleteIcon from "@mui/icons-material/Delete";
import AddIcon from "@mui/icons-material/Add";
import useAdminService from "../service/useAdminService";

import { FaqInterface } from "../types";
import ErrorMessage from "../components/ErrorMessage/ErrorMessage";

const style = {
  display: "flex",
  flexDirection: "column",
  position: "absolute",
  top: "30%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
};

const Faq = () => {
  const [isOpenModal, setIsOpenModal] = useState<boolean>(false);
  const [faq, setFaq] = useState<FaqInterface[]>([]);
  const [question, setQuestion] = useState<string>("");
  const [answer, setAnswer] = useState<string>("");

  const { getFaq, addFag, removeFaq, loading, error } = useAdminService();

  useEffect(() => {
    updateFaq();
  }, []);

  const updateFaq = () => {
    getFaq().then(onFaqLoaded);
  };

  const onFaqLoaded = (faq: FaqInterface[]) => {
    setFaq(faq);
  };

  const handleChange = (
    event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>,
    setFunc: Dispatch<string>
  ) => {
    setFunc(event.target.value);
  };

  const addFaq = () => {
    addFag(JSON.stringify({ question, answer }));
    updateFaq();
    setQuestion("");
    setAnswer("");
    setIsOpenModal(false);
    updateFaq();
  };

  const onDeleteFaq = (id: number, question: string, answer: string) => {
    // setFaq(faq.filter((er, el) => el !== id));
    const body = { id: id, question: question, answer: answer };
    removeFaq(JSON.stringify(body)).then((res) => console.log(res));
    updateFaq();
    updateFaq();
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
            Добавление вопроса
          </Typography>
          <TextField
            id="outlined-name"
            label="Вопрос"
            value={question}
            onChange={(event) => handleChange(event, setQuestion)}
            autoFocus={true}
            required={true}
            sx={{ marginBottom: 2 }}
          />
          <TextField
            id="outlined-uncontrolled"
            label="Ответ"
            value={answer}
            onChange={(event) => handleChange(event, setAnswer)}
            required={true}
          />
          <Button onClick={() => addFaq()}>Добавить</Button>
        </Box>
      </Modal>
      <Typography
        component={"h2"}
        gutterBottom
        align={"left"}
        color={"white"}
        sx={{ ml: 10, fontWeight: 700, position: "absolute", top: "20px" }}
      >
        Вопросы и ответы
      </Typography>
      <List>
        {loading ? <CircularProgress /> : null}
        {error ? <ErrorMessage /> : null}
        {faq
          ? faq.map((el) => (
              <ListItem
                key={el.id}
                secondaryAction={
                  <IconButton
                    edge="end"
                    aria-label="delete"
                    onClick={() => onDeleteFaq(el.id, el.question, el.answer)}
                  >
                    <DeleteIcon />
                  </IconButton>
                }
                divider={true}
                sx={{ width: "70%", margin: "0 auto" }}
              >
                <ListItemText
                  primary={`Вопрос: ${el.question}`}
                  secondary={`Ответ: ${el.answer || "Необходимо дать ответ"}`}
                />
              </ListItem>
            ))
          : null}
      </List>
      <IconButton
        aria-label="add"
        size="large"
        onClick={() => setIsOpenModal(true)}
        disabled={loading || error || false}
      >
        <AddIcon fontSize="inherit" />
      </IconButton>
    </>
  );
};

export default Faq;
