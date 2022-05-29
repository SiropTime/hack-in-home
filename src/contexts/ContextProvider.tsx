import {createContext, ReactElement, useContext, useState} from "react";
import {ContextInterface, Role, User} from "./types";

const initState  = {
    user:{
        name: 'User',
        surname: 'Super',
        avatar: 'https://upload.wikimedia.org/wikipedia/commons/6/65/Eduard_Sch%C3%B6nfeld_avka_de.de.JPG',
        role: Role.Headman,
        email: 'admin@mail.com',
    },
    isAuth: false,
}

const StateContext = createContext<ContextInterface | null>(null);

export const ContextProvider = ({children} : {children: ReactElement}) => {
    const [isOpenActiveMenu, setIsOpenActiveMenu] = useState<boolean>(false);
    const [isAuth, setIsAuth] = useState<boolean>(true)

    return (
        // eslint-disable-next-line react/react-in-jsx-scope
       <StateContext.Provider value={{isOpenActiveMenu, setIsOpenActiveMenu, isAuth, user: initState.user}}>
           {children}
       </StateContext.Provider>
    )
}

export const useStateContext = () => useContext(StateContext);