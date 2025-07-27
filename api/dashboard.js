export default async function handler(req, res) {
  // Set CORS headers
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type, Authorization');

  // Handle preflight requests
  if (req.method === 'OPTIONS') {
    res.status(200).end();
    return;
  }

  // Mock data for development/testing
  const mockData = {
    staffAttendance: {
      present: 45,
      absent: 3,
      total: 48,
      percentage: 93.8
    },
    studentAttendance: {
      present: 892,
      absent: 23,
      total: 915,
      percentage: 97.5
    },
    behavior: {
      incidents: 2,
      positive: 15,
      total: 17
    },
    upcomingEvents: [
      {
        id: "1",
        title: "Parent-Teacher Conference",
        date: "2024-01-15",
        time: "14:00"
      },
      {
        id: "2", 
        title: "School Assembly",
        date: "2024-01-18",
        time: "09:00"
      },
      {
        id: "3",
        title: "Sports Day",
        date: "2024-01-22",
        time: "10:00"
      }
    ],
    schoolDaysLeft: 45,
    lastUpdated: new Date().toISOString()
  };

  try {
    if (req.method === 'GET') {
      res.status(200).json(mockData);
    } else if (req.method === 'POST') {
      // For now, just return the same data
      // In production, this would update the backend
      res.status(200).json(mockData);
    } else {
      res.status(405).json({ error: 'Method not allowed' });
    }
  } catch (error) {
    console.error('Error:', error);
    res.status(500).json({ 
      error: 'Internal Server Error',
      message: error.message 
    });
  }
} 