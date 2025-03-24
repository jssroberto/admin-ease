import React, { useState, useEffect } from 'react';
import { X, Info } from 'lucide-react';

interface MessageAlertProps {
  message: string;
  onClose?: () => void;
}

const MessageAlert: React.FC<MessageAlertProps> = ({ message, onClose }) => {
  const [isVisible, setIsVisible] = useState(false);
  const [isRendered, setIsRendered] = useState(true);
  
  useEffect(() => {
    const timer = setTimeout(() => {
      setIsVisible(true);
    }, 10);
    
    return () => clearTimeout(timer);
  }, []);

  const handleClose = () => {
    setIsVisible(false);
    
    setTimeout(() => {
      setIsRendered(false);
      if (onClose) onClose();
    }, 300); 
  };

  if (!isRendered) return null;

  return (
    <div 
      className={`fixed top-0 left-0 right-0 mx-auto z-50 max-w-md transition-transform duration-300 ease-out ${
        isVisible ? 'transform translate-y-4' : 'transform -translate-y-full'
      }`}
    >
      <div className="bg-blue-50 border border-blue-200 rounded-lg p-4 shadow-lg mx-4">
        <div className="flex items-start gap-3">
          <div className="flex-shrink-0">
            <Info className="h-5 w-5 text-blue-500" />
          </div>
          
          <div className="flex-1">
            <p className="text-sm text-blue-700">{message}</p>
          </div>
          
          <button
            onClick={handleClose}
            className="flex-shrink-0 ml-2 -mt-1"
            aria-label="Close"
          >
            <X className="h-4 w-4 text-blue-400 hover:text-blue-600" />
          </button>
        </div>
        
        <div className="mt-3 flex justify-end">
          <button
            onClick={handleClose}
            className="px-3 py-1.5 text-xs font-medium text-white bg-blue-500 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
          >
            OK
          </button>
        </div>
      </div>
    </div>
  );
};

export default MessageAlert;