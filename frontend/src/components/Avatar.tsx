import React from "react";

interface AvatarProps {
  name: string;
  role?: string;
  img?: string;
  initials?: string;
}

export default function Avatar({ name, role = "Admin", img, initials }: AvatarProps) {
  const placeholder = "/images/avatar-placeholder.png";
  const avatarContent = img ? (
    <img src={img} alt={name} className="w-8 h-8 rounded-full" />
  ) : initials ? (
    <div className="w-8 h-8 flex items-center justify-center text-white rounded-full text-sm font-bold">
      {initials}
    </div>
  ) : (
    <img src={placeholder} alt="Placeholder" className="w-8 h-8 rounded-full" />
  );

  return (
    <div className="flex items-center gap-2">
      {avatarContent}
      <div className="flex flex-col">
        <span className="block text-sm font-normal">{name}</span>
        <span className="text-xs text-yellow-600">{role}</span>
      </div>
    </div>
  );
}
