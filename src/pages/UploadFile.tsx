import React from "react";
import {Button, List, ListItem, Stack, styled, Typography} from "@mui/material";

const Input = styled('input')({
    display: 'none',
});

const UploadFile = () => {
  return (
    <>
      <Typography
        component={"h2"}
        gutterBottom
        align={"left"}
        color={"white"}
        sx={{ ml: 10, fontWeight: 700, position: "absolute", top: "20px" }}
      >
        Загрузка нормативных документов
      </Typography>
        <List>
            <ListItem>
                <Typography sx={{ml: 2, mr: 3}}>Загрузка расписания</Typography>
                <Stack direction="row" alignItems="center" spacing={2}>
                    <label htmlFor="contained-button-file">
                        <Input id="contained-button-file" multiple type="file" />
                        <Button variant="contained" component="span">
                            Upload
                        </Button>
                    </label>
                </Stack>
            </ListItem>
        </List>
    </>
  );
};

export default UploadFile;
