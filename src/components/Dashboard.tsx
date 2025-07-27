import React, { useState, useEffect } from 'react';
import { Users, GraduationCap, Heart, Calendar, Clock, RefreshCw } from 'lucide-react';
import { DashboardData, UpdateRequest } from '../types/dashboard';
import { dashboardAPI } from '../services/api';
import { MetricCard } from './MetricCard';
import { AttendanceDisplay } from './AttendanceDisplay';
import { BehaviorDisplay } from './BehaviorDisplay';
import { EventsList } from './EventsList';
import { AdminPanel } from './AdminPanel';

export const Dashboard: React.FC = () => {
  const [data, setData] = useState<DashboardData | null>(null);
  const [loading, setLoading] = useState(true);
  const [lastUpdated, setLastUpdated] = useState<string>('');

  const loadData = async () => {
    try {
      setLoading(true);
      const dashboardData = await dashboardAPI.getDashboardData();
      setData(dashboardData);
      setLastUpdated(new Date(dashboardData.lastUpdated).toLocaleTimeString());
    } catch (error) {
      console.error('Failed to load dashboard data:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleUpdate = async (update: UpdateRequest) => {
    try {
      const updatedData = await dashboardAPI.updateDashboardData(update);
      setData(updatedData);
      setLastUpdated(new Date(updatedData.lastUpdated).toLocaleTimeString());
    } catch (error) {
      console.error('Failed to update dashboard data:', error);
    }
  };

  useEffect(() => {
    loadData();
    
    // Auto-refresh every 30 seconds
    const interval = setInterval(loadData, 30000);
    return () => clearInterval(interval);
  }, []);

  if (loading || !data) {
    return (
      <div className="min-h-screen bg-gradient-to-br from-blue-50 via-white to-purple-50 flex items-center justify-center">
        <div className="text-center">
          <RefreshCw className="w-12 h-12 text-blue-600 animate-spin mx-auto mb-4" />
          <p className="text-gray-600">Loading dashboard...</p>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 via-white to-purple-50">
      {/* Header */}
      <header className="bg-white/80 backdrop-blur-sm shadow-sm border-b border-white/20">
        <div className="container mx-auto px-6 py-4">
          <div className="flex items-center justify-between">
            <div>
              <h1 className="text-3xl font-bold text-gray-800">School Dashboard</h1>
              <p className="text-gray-600">Real-time monitoring system</p>
            </div>
            <div className="text-right">
              <div className="text-sm text-gray-500">Last updated</div>
              <div className="text-lg font-semibold text-gray-800">{lastUpdated}</div>
            </div>
          </div>
        </div>
      </header>

      {/* Main Content */}
      <main className="container mx-auto px-6 py-8">
        <div className="grid grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 gap-8">
          {/* Staff Attendance */}
          <MetricCard title="Staff Attendance" icon={Users}>
            <AttendanceDisplay 
              present={data.staffAttendance.present}
              absent={data.staffAttendance.absent}
            />
          </MetricCard>

          {/* Student Attendance */}
          <MetricCard title="Student Attendance" icon={GraduationCap}>
            <AttendanceDisplay 
              present={data.studentAttendance.present}
              absent={data.studentAttendance.absent}
            />
          </MetricCard>

          {/* School Days Left */}
          <MetricCard title="School Days Remaining" icon={Clock} className="lg:col-span-2 xl:col-span-1">
            <div className="text-center py-4">
              <div className="text-6xl font-bold text-blue-600 mb-2">
                {data.schoolDaysLeft}
              </div>
              <div className="text-gray-600">Days until summer break</div>
              <div className="mt-4 w-full bg-gray-200 rounded-full h-3">
                <div 
                  className="bg-gradient-to-r from-blue-500 to-purple-600 h-3 rounded-full transition-all duration-500"
                  style={{ width: `${Math.max(0, 100 - (data.schoolDaysLeft / 180) * 100)}%` }}
                ></div>
              </div>
            </div>
          </MetricCard>

          {/* Behavior Reports */}
          <MetricCard title="Behavior Reports" icon={Heart} className="lg:col-span-1">
            <BehaviorDisplay 
              middleSchool={data.behavior.middleSchool}
              elementary={data.behavior.elementary}
            />
          </MetricCard>

          {/* Upcoming Events */}
          <MetricCard title="Upcoming Events" icon={Calendar} className="lg:col-span-1 xl:col-span-2">
            <EventsList events={data.upcomingEvents} />
          </MetricCard>
        </div>
      </main>

      {/* Admin Panel */}
      <AdminPanel data={data} onUpdate={handleUpdate} />
    </div>
  );
};