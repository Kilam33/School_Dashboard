export interface DashboardData {
  staffAttendance: {
    present: number;
    absent: number;
  };
  studentAttendance: {
    present: number;
    absent: number;
  };
  behavior: {
    middleSchool: {
      positive: number;
      negative: number;
    };
    elementary: {
      positive: number;
      negative: number;
    };
  };
  upcomingEvents: Array<{
    id: string;
    title: string;
    date: string;
    time: string;
  }>;
  schoolDaysLeft: number;
  lastUpdated: string;
}

export interface UpdateRequest {
  type: 'staff' | 'student' | 'behavior' | 'events' | 'daysLeft';
  data: any;
}