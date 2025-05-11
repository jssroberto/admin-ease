import { JSX, useEffect, useState } from "react";
import Avatar from "./Avatar";

function Navbar(): JSX.Element {
  const [date, setDate] = useState<string>("");

  useEffect(() => {
    const updateDate = () => {
      const currentDate: Date = new Date();
      const formattedDate: string = currentDate.toLocaleString("es-ES", {
        weekday: "long",
        day: "numeric",
        month: "long",
        year: "numeric",
        hour: "2-digit",
        minute: "2-digit",
      });
      setDate(formattedDate.charAt(0).toUpperCase() + formattedDate.slice(1));
    };

    updateDate(); 
    const intervalId = setInterval(updateDate, 1000);

    return () => clearInterval(intervalId); 
  }, []);

  return (
    <nav className="text-white flex justify-between items-center px-6 py-2 bg-[url('/images/bg-pattern.png')] bg-cover bg-center">
      <img src="/images/logo.png" alt="Moi Moi" className="h-10 w-auto" />
      <div className="flex items-center gap-6">
        <span className="text-sm text-gray-300">{date}</span>
        <div className="flex items-center gap-2">
          <Avatar name={"Carlos Sanchez"} />
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
