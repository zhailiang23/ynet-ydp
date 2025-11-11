import { Card, CardContent } from "@/components/ui/card"
import type React from "react"

interface ScenarioOptionCardProps {
  icon: React.ReactNode
  title: string
  description: string
}

export function ScenarioOptionCard({ icon, title, description }: ScenarioOptionCardProps) {
  return (
    <Card className="w-full rounded-lg border border-gray-700 bg-[#2a2a2a] text-white shadow-md hover:border-blue-600 hover:shadow-lg">
      <CardContent className="flex items-start gap-4 p-4">
        <div className="flex-shrink-0 rounded-md bg-gray-700 p-2 text-gray-300">{icon}</div>
        <div>
          <h3 className="mb-1 text-base font-medium">{title}</h3>
          <p className="text-sm text-gray-400">{description}</p>
        </div>
      </CardContent>
    </Card>
  )
}
