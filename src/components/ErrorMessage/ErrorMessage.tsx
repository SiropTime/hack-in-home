import React from "react";

import error from "./errorMessage.gif";

const ErrorMessage = () => {
  return (
    <>
      <img
        style={{
          display: "block",
          width: "250px",
          height: "250px",
          margin: "0 auto",
          objectFit: "contain",
        }}
        src={error}
        alt="error"
      />
    </>
  );
};

export default ErrorMessage;