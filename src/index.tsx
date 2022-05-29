import React from "react";
import ReactDOM from "react-dom/client";

import { StyledEngineProvider } from "@mui/material/styles";

import { ContextProvider } from "./contexts/ContextProvider";

import App from "./App";
import "./index.css";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);
root.render(
  <React.StrictMode>
    <StyledEngineProvider injectFirst>
      <ContextProvider>
        <App />
      </ContextProvider>
    </StyledEngineProvider>
  </React.StrictMode>
);
