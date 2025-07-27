import { DashboardData, UpdateRequest } from '../types/dashboard';

// API Configuration
const API_BASE_URL = import.meta.env.PROD 
  ? '/api' 
  : 'http://localhost:8080/api';

export const dashboardAPI = {
  async getDashboardData(): Promise<DashboardData> {
    try {
      const response = await fetch(`${API_BASE_URL}/dashboard`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      
      const data = await response.json();
      
      // Transform the data to match frontend expectations
      return {
        staffAttendance: data.staffAttendance,
        studentAttendance: data.studentAttendance,
        behavior: data.behavior,
        upcomingEvents: data.upcomingEvents.map((event: any) => ({
          id: event.id.toString(),
          title: event.title,
          date: event.date,
          time: event.time
        })),
        schoolDaysLeft: data.schoolDaysLeft,
        lastUpdated: data.lastUpdated
      };
    } catch (error) {
      console.error('Failed to fetch dashboard data:', error);
      throw error;
    }
  },

  async updateDashboardData(update: UpdateRequest): Promise<DashboardData> {
    try {
      const response = await fetch(`${API_BASE_URL}/dashboard`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(update),
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      
      const data = await response.json();
      
      // Transform the data to match frontend expectations
      return {
        staffAttendance: data.staffAttendance,
        studentAttendance: data.studentAttendance,
        behavior: data.behavior,
        upcomingEvents: data.upcomingEvents.map((event: any) => ({
          id: event.id.toString(),
          title: event.title,
          date: event.date,
          time: event.time
        })),
        schoolDaysLeft: data.schoolDaysLeft,
        lastUpdated: data.lastUpdated
      };
    } catch (error) {
      console.error('Failed to update dashboard data:', error);
      throw error;
    }
  }
};