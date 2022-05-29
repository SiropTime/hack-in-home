import React from "react";
import { DataGrid, GridValueGetterParams } from "@mui/x-data-grid";
import {Typography} from "@mui/material";

const columns = [
  { field: "firstName", headerName: "Фамилия", width: 130 },
  { field: "lastName", headerName: "Имя", width: 130 },
  { field: "patronymic", headerName: "Отчество", width: 130 },
  {
    field: "age",
    headerName: "Возраст",
    type: "number",
    width: 90,
  },
  {
    field: "fullName",
    headerName: "ФИО",
    description: "This column has a value getter and is not sortable.",
    sortable: false,
    width: 250,
    valueGetter: (params: GridValueGetterParams) =>
      `${params.row.firstName || ""} ${params.row.lastName || ""} ${
        params.row.patronymic || ""
      }`,
  },
  {
    field: "group",
    headerName: "Группа",
    type: "string",
    width: 120,
  },
  {
    field: "debt",
    headerName: 'Задолженности',
    type: 'boolean',
    width: 120,
  },
];

const rows = [
  {
    id: 1,
    lastName: "Алексей",
    firstName: "Осипов",
    patronymic: "Дмитриевич",
    age: 19,
    group: "ИДБ-20-10",
    debt: true,
  },
  {
    id: 2,
    lastName: "Иван",
    firstName: "Иванов",
    patronymic: "Иванович",
    age: 42,
    group: "ИДБ-20-10",
  },
  {
    id: 3,
    lastName: "Иван",
    firstName: "Иванов",
    patronymic: "Иванович",
    age: 45,
    group: "ИДБ-20-10",
  },
  { id: 4,  lastName: "Иван",
    firstName: "Иванов",
    patronymic: "Иванович", age: 16, group: "ИДБ-20-10" },
  {
    id: 5,
    lastName: "Иван",
    firstName: "Иванов",
    patronymic: "Иванович",
    age: null,
    group: "ИДБ-20-10",
  },
  {
    id: 6,
    lastName: "Иван",
    firstName: "Иванов",
    patronymic: "Иванович",
    age: 150,
    group: "ИДБ-20-10",
  },
  {
    id: 7,
    lastName: "Иван",
    firstName: "Иванов",
    patronymic: "Иванович",
    age: 44,
    group: "ИДБ-20-10",
  },
  {
    id: 8,
    lastName: "Иван",
    firstName: "Иванов",
    patronymic: "Иванович",
    age: 36,
    group: "ИДБ-20-10",
  },
  {
    id: 9,
    lastName: "Иван",
    firstName: "Иванов",
    patronymic: "Иванович",
    age: 65,
    group: "ИДБ-20-10",
  },
];

export default function DataTable() {
  return (
      <>
        <Typography
            component={"h2"}
            gutterBottom
            align={"left"}
            color={'white'}
            sx={{ ml: 10, fontWeight: 700, position: 'absolute', top: '20px' }}
        >
          Студенты
        </Typography>
        <div style={{ height: 400, width: "80%", marginTop: 2, margin: "0 auto" }}>
          <DataGrid
              rows={rows}
              columns={columns}
              pageSize={5}
              rowsPerPageOptions={[5]}
              checkboxSelection
          />
        </div>
      </>

  );
}

//todo: сделать возможность делать отчет с должниками
