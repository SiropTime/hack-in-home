import React, { useEffect, useState } from "react";
import {
  Accordion,
  AccordionDetails,
  AccordionSummary,
  CircularProgress,
  List,
  ListItem,
  ListItemText,
  Typography,
} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import { Link } from "react-router-dom";
import useAdminService from "../service/useAdminService";
import { FaqInterface } from "../types";
import ErrorMessage from "../components/ErrorMessage/ErrorMessage";
import RequisitionList from "../components/RequisitionList/RequisitionList";

const MainPage = () => {
  const { getFaq, loading, error } = useAdminService();

  const [faq, setFaq] = useState<FaqInterface[]>([]);

  useEffect(() => {
    updateFaq();
  }, []);

  const updateFaq = () => {
    getFaq().then(onFaqLoaded);
  };

  const onFaqLoaded = (faq: FaqInterface[]) => {
    setFaq(faq);
  };

  return (
    <>
      <Typography
        component={"h2"}
        gutterBottom
        align={"left"}
        color={"white"}
        sx={{ ml: 10, fontWeight: 700, position: "absolute", top: "20px" }}
      >
        Dashboard
      </Typography>
      <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1a-content"
          id="panel1a-header"
        >
          <Typography variant={"h6"} sx={{ margin: "0 auto" }}>
            Вопросы, требующие ответа
          </Typography>
        </AccordionSummary>
        <AccordionDetails>
          <List>
            {loading ? <CircularProgress /> : null}
            {error ? <ErrorMessage /> : null}
            {faq
              ? faq.map((el) =>
                  !el.answer ? (
                    <Link to={"./faq"} style={{ textDecoration: "none" }}>
                      <ListItem
                        key={el.id}
                        divider={true}
                        sx={{ width: "70%", margin: "0 auto" }}
                      >
                        <ListItemText
                          primary={`Вопрос: ${el.question}`}
                          secondary={`Ответ: ${
                            el.answer || "Необходимо дать ответ"
                          }`}
                          sx={{ color: "black" }}
                        />
                      </ListItem>
                    </Link>
                  ) : null
                )
              : null}
          </List>
        </AccordionDetails>
      </Accordion>
      <Accordion>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel2a-content"
          id="panel2a-header"
        >
          <Typography variant={"h6"} sx={{ margin: "0 auto" }}>
            Заявления
          </Typography>
        </AccordionSummary>
        <AccordionDetails>
          <RequisitionList/>
        </AccordionDetails>
      </Accordion>
    </>
  );
};

export default MainPage;
