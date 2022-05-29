import {
  Button,
  Card,
  CardActions,
  CardContent,
  CircularProgress,
  List,
  ListItem,
  ListItemText,
  Typography,
} from "@mui/material";
import ErrorMessage from "../ErrorMessage/ErrorMessage";
import React, { useEffect, useState } from "react";
import useAdminService from "../../service/useAdminService";

enum RequisitionType {
  Army = "ARMY",
  Profcom = "PROFCOM",
  Studying = "STUDIYING",
}

const RequisitionList = () => {
  const { loading, getRequisition, error } = useAdminService();

  const [requisition, setRequisition] = useState<any>([]);

  useEffect(() => {
    updateRequisition();
  }, []);

  const updateRequisition = () => {
    getRequisition().then(onRequisition);
  };

  const onRequisition = (requisition: any) => {
    setRequisition(requisition);
  };
  return (
    <List sx={{ display: "flex" }}>
      {loading ? <CircularProgress /> : null}
      {error ? <ErrorMessage /> : null}
      {requisition
        ? requisition.map((el: any) => (
            <Card
              key={el.key}
              sx={{ width: 300, margin: "0 auto", marginBottom: 2 }}
              variant="outlined"
            >
              <CardContent>
                <Typography
                  sx={{ fontSize: 14 }}
                  color="text.secondary"
                  gutterBottom
                >
                  Заявление в
                  {el.type === RequisitionType.Profcom
                    ? " Профком"
                    : el.type === RequisitionType.Army
                    ? "о Второй отдел"
                    : " Деканат"}
                </Typography>
                <Typography variant="h5" component="div">
                  {`${el.studentId.surname} ${el.studentId.name} ${el.studentId.middlename}`}
                </Typography>
                <Typography sx={{ mb: 1.5 }} color="text.secondary">
                  {el.studentId.email}
                </Typography>
                <Typography variant="body2">
                  {el.studentId.groupId.name}
                  <br />
                </Typography>
              </CardContent>
              <CardActions>
                <Button size="small" color={"error"}>
                  Отклонить заявление
                </Button>
              </CardActions>
            </Card>
          ))
        : null}
    </List>
  );
};

export default RequisitionList;
