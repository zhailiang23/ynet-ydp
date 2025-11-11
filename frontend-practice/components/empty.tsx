"use client"

import type React from "react"

import { FileX, Plus, Search, ShoppingCart, Users, Inbox } from "lucide-react"
import { Button } from "@/components/ui/button"

interface EmptyProps {
  icon?: "file" | "search" | "cart" | "users" | "inbox" | "custom"
  title?: string
  description?: string
  action?: {
    label: string
    onClick: () => void
  }
  customIcon?: React.ReactNode
  className?: string
}

const iconMap = {
  file: FileX,
  search: Search,
  cart: ShoppingCart,
  users: Users,
  inbox: Inbox,
  custom: null,
}

export function Empty({
  icon = "file",
  title = "No data found",
  description = "There's nothing to show here yet.",
  action,
  customIcon,
  className = "",
}: EmptyProps) {
  const IconComponent = iconMap[icon]

  return (
    <div className={`flex flex-col items-center justify-center p-8 text-center ${className}`}>
      <div className="mb-4 rounded-full bg-muted p-4">
        {icon === "custom" && customIcon ? (
          customIcon
        ) : IconComponent ? (
          <IconComponent className="h-8 w-8 text-muted-foreground" />
        ) : (
          <FileX className="h-8 w-8 text-muted-foreground" />
        )}
      </div>

      <h3 className="mb-2 text-lg font-semibold text-foreground">{title}</h3>

      <p className="mb-6 max-w-sm text-sm text-muted-foreground">{description}</p>

      {action && (
        <Button onClick={action.onClick} className="gap-2">
          <Plus className="h-4 w-4" />
          {action.label}
        </Button>
      )}
    </div>
  )
}
