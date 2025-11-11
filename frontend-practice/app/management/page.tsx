"use client" // This page needs to be a client component to use useState

import { useState, useEffect } from "react"
import { SiteHeader } from "@/components/site-header"
import { ManagementSidebar } from "@/components/management-sidebar"
import { SalesRoutineManagement } from "@/components/sales-routine-management"
import { ScriptManagement } from "@/components/script-management"
import { CaseLibraryManagement } from "@/components/case-library-management"
import { VirtualCustomerRoleManagement } from "@/components/virtual-customer-role-management"
import { ScenarioCreationManagement } from "@/components/scenario-creation-management"
import { ManagementDashboardContent } from "@/components/management-dashboard-content"
import { ScriptVersionList } from "@/components/script-version-list" // Import the new ScriptVersionList
import { useSearchParams } from "next/navigation" // Import useSearchParams

export default function ManagementPage() {
  const searchParams = useSearchParams()
  const initialModule = searchParams.get("module") || "dashboard"
  const [activeModule, setActiveModule] = useState(initialModule) // Default active module to dashboard

  // Update activeModule when URL search params change
  useEffect(() => {
    const moduleFromUrl = searchParams.get("module")
    if (moduleFromUrl && moduleFromUrl !== activeModule) {
      setActiveModule(moduleFromUrl)
    }
  }, [searchParams, activeModule])

  const renderContent = () => {
    switch (activeModule) {
      case "dashboard":
        return <ManagementDashboardContent />
      case "virtual-customer-roles":
        return <VirtualCustomerRoleManagement />
      case "scenario-creation":
        return <ScenarioCreationManagement />
      case "sales-routines":
        return <SalesRoutineManagement />
      case "script-management": // This is now "创建剧本"
        return <ScriptManagement />
      case "script-versions": // This is now "剧本版本管理"
        return <ScriptVersionList />
      case "case-library":
        return <CaseLibraryManagement />
      case "trainee-management": // This module is handled by direct navigation, but included for completeness
        return (
          <div className="text-center py-20">
            <h2 className="text-2xl font-semibold text-gray-300">学员培训管理</h2>
            <p className="text-gray-400 mt-2">请通过左侧菜单直接导航到此页面。</p>
          </div>
        )
      default:
        return (
          <div className="text-center py-20">
            <h2 className="text-2xl font-semibold text-gray-300">模块未找到</h2>
            <p className="text-gray-400 mt-2">请检查您的选择。</p>
          </div>
        )
    }
  }

  return (
    <div
      className="min-h-screen bg-[#1a1a1a] text-white"
      style={{
        backgroundImage:
          "linear-gradient(to right, #2a2a2a 1px, transparent 1px), linear-gradient(to bottom, #2a2a2a 1px, transparent 1px)",
        backgroundSize: "40px 40px",
      }}
    >
      <SiteHeader />
      <div className="flex min-h-[calc(100vh-64px)]">
        <ManagementSidebar activeModule={activeModule} setActiveModule={setActiveModule} />
        <main className="flex-1 p-8">
          <h1 className="mb-8 text-3xl font-bold text-gray-200">管理后台</h1>
          {renderContent()}
        </main>
      </div>
    </div>
  )
}
