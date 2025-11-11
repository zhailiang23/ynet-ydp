import { Card, CardFooter } from "@/components/ui/card"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import Link from "next/link"

interface CoachCardProps {
  id: string
  name: string
  avatarSrc: string
  roles: string[]
  description: string
}

export function CoachCard({ id, name, avatarSrc, roles, description }: CoachCardProps) {
  return (
    <Card className="flex flex-col items-center justify-between rounded-lg border border-gray-700 bg-[#2a2a2a] p-6 text-center text-white shadow-lg">
      <div className="flex flex-col items-center">
        <Avatar className="mb-4 h-24 w-24 border-2 border-gray-600">
          <AvatarImage src={avatarSrc || "/placeholder.svg"} alt={name} />
          <AvatarFallback>{name.substring(0, 1)}</AvatarFallback>
        </Avatar>
        <h3 className="mb-2 text-xl font-semibold text-gray-200">{name}</h3>
        <div className="mb-3 flex flex-wrap justify-center gap-2">
          {roles.map((role, index) => (
            <Badge key={index} className="bg-blue-600 text-white hover:bg-blue-700">
              {role}
            </Badge>
          ))}
        </div>
        <p className="text-sm text-gray-400">{description}</p>
      </div>
      <CardFooter className="mt-6 w-full p-0">
        <Link href="/course" className="w-full">
          <Button className="w-full rounded-full bg-purple-600 text-white hover:bg-purple-700">选择</Button>
        </Link>
      </CardFooter>
    </Card>
  )
}
