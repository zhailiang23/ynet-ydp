"use client"

import { Empty } from "./components/empty"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { Heart } from "lucide-react"

export default function Component() {
  return (
    <div className="container mx-auto p-6 space-y-8">
      <div className="text-center space-y-2">
        <h1 className="text-3xl font-bold">Empty State Components</h1>
        <p className="text-muted-foreground">Various examples of empty state components</p>
      </div>

      <Tabs defaultValue="basic" className="w-full">
        <TabsList className="grid w-full grid-cols-4">
          <TabsTrigger value="basic">Basic</TabsTrigger>
          <TabsTrigger value="with-action">With Action</TabsTrigger>
          <TabsTrigger value="different-icons">Icons</TabsTrigger>
          <TabsTrigger value="custom">Custom</TabsTrigger>
        </TabsList>

        <TabsContent value="basic" className="space-y-4">
          <Card>
            <CardHeader>
              <CardTitle>Basic Empty State</CardTitle>
              <CardDescription>Simple empty state with default styling</CardDescription>
            </CardHeader>
            <CardContent>
              <Empty />
            </CardContent>
          </Card>
        </TabsContent>

        <TabsContent value="with-action" className="space-y-4">
          <Card>
            <CardHeader>
              <CardTitle>Empty State with Action</CardTitle>
              <CardDescription>Empty state with a call-to-action button</CardDescription>
            </CardHeader>
            <CardContent>
              <Empty
                title="No products found"
                description="You haven't added any products yet. Start by creating your first product."
                action={{
                  label: "Add Product",
                  onClick: () => alert("Add product clicked!"),
                }}
              />
            </CardContent>
          </Card>
        </TabsContent>

        <TabsContent value="different-icons" className="space-y-4">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <Card>
              <CardHeader>
                <CardTitle>Search Results</CardTitle>
              </CardHeader>
              <CardContent>
                <Empty
                  icon="search"
                  title="No results found"
                  description="Try adjusting your search terms or filters."
                />
              </CardContent>
            </Card>

            <Card>
              <CardHeader>
                <CardTitle>Shopping Cart</CardTitle>
              </CardHeader>
              <CardContent>
                <Empty
                  icon="cart"
                  title="Your cart is empty"
                  description="Add some items to your cart to get started."
                  action={{
                    label: "Start Shopping",
                    onClick: () => alert("Start shopping clicked!"),
                  }}
                />
              </CardContent>
            </Card>

            <Card>
              <CardHeader>
                <CardTitle>Team Members</CardTitle>
              </CardHeader>
              <CardContent>
                <Empty
                  icon="users"
                  title="No team members"
                  description="Invite team members to collaborate on this project."
                  action={{
                    label: "Invite Members",
                    onClick: () => alert("Invite members clicked!"),
                  }}
                />
              </CardContent>
            </Card>

            <Card>
              <CardHeader>
                <CardTitle>Inbox</CardTitle>
              </CardHeader>
              <CardContent>
                <Empty icon="inbox" title="Inbox is empty" description="You're all caught up! No new messages." />
              </CardContent>
            </Card>
          </div>
        </TabsContent>

        <TabsContent value="custom" className="space-y-4">
          <Card>
            <CardHeader>
              <CardTitle>Custom Icon</CardTitle>
              <CardDescription>Empty state with a custom icon</CardDescription>
            </CardHeader>
            <CardContent>
              <Empty
                icon="custom"
                customIcon={<Heart className="h-8 w-8 text-red-500" />}
                title="No favorites yet"
                description="Items you favorite will appear here for quick access."
                action={{
                  label: "Browse Items",
                  onClick: () => alert("Browse items clicked!"),
                }}
              />
            </CardContent>
          </Card>
        </TabsContent>
      </Tabs>
    </div>
  )
}
