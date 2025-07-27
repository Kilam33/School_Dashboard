import React, { useState } from 'react';
import { Settings, Save, X } from 'lucide-react';
import { DashboardData, UpdateRequest } from '../types/dashboard';

interface AdminPanelProps {
  data: DashboardData;
  onUpdate: (update: UpdateRequest) => Promise<void>;
}

export const AdminPanel: React.FC<AdminPanelProps> = ({ data, onUpdate }) => {
  const [isOpen, setIsOpen] = useState(false);
  const [loading, setLoading] = useState(false);
  
  const [staffPresent, setStaffPresent] = useState(data.staffAttendance.present);
  const [staffAbsent, setStaffAbsent] = useState(data.staffAttendance.absent);
  const [studentPresent, setStudentPresent] = useState(data.studentAttendance.present);
  const [studentAbsent, setStudentAbsent] = useState(data.studentAttendance.absent);
  const [daysLeft, setDaysLeft] = useState(data.schoolDaysLeft);

  const handleSave = async (type: UpdateRequest['type'], updateData: any) => {
    setLoading(true);
    try {
      await onUpdate({ type, data: updateData });
    } finally {
      setLoading(false);
    }
  };

  if (!isOpen) {
    return (
      <button
        onClick={() => setIsOpen(true)}
        className="fixed bottom-6 right-6 bg-blue-600 hover:bg-blue-700 text-white p-4 rounded-full shadow-lg transition-all duration-200 hover:scale-105"
      >
        <Settings className="w-6 h-6" />
      </button>
    );
  }

  return (
    <div className="fixed inset-0 bg-black/50 backdrop-blur-sm z-50 flex items-center justify-center p-4">
      <div className="bg-white rounded-xl shadow-2xl w-full max-w-2xl max-h-[90vh] overflow-y-auto">
        <div className="flex items-center justify-between p-6 border-b">
          <h2 className="text-xl font-bold text-gray-800">Admin Panel</h2>
          <button
            onClick={() => setIsOpen(false)}
            className="p-2 hover:bg-gray-100 rounded-lg transition-colors"
          >
            <X className="w-5 h-5" />
          </button>
        </div>
        
        <div className="p-6 space-y-8">
          {/* Staff Attendance */}
          <div className="space-y-4">
            <h3 className="text-lg font-semibold text-gray-800">Staff Attendance</h3>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">Present</label>
                <input
                  type="number"
                  value={staffPresent}
                  onChange={(e) => setStaffPresent(Number(e.target.value))}
                  className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">Absent</label>
                <input
                  type="number"
                  value={staffAbsent}
                  onChange={(e) => setStaffAbsent(Number(e.target.value))}
                  className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                />
              </div>
            </div>
            <button
              onClick={() => handleSave('staff', { present: staffPresent, absent: staffAbsent })}
              disabled={loading}
              className="flex items-center gap-2 bg-blue-600 hover:bg-blue-700 disabled:bg-blue-400 text-white px-4 py-2 rounded-lg transition-colors"
            >
              <Save className="w-4 h-4" />
              Update Staff
            </button>
          </div>

          {/* Student Attendance */}
          <div className="space-y-4">
            <h3 className="text-lg font-semibold text-gray-800">Student Attendance</h3>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">Present</label>
                <input
                  type="number"
                  value={studentPresent}
                  onChange={(e) => setStudentPresent(Number(e.target.value))}
                  className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">Absent</label>
                <input
                  type="number"
                  value={studentAbsent}
                  onChange={(e) => setStudentAbsent(Number(e.target.value))}
                  className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
                />
              </div>
            </div>
            <button
              onClick={() => handleSave('student', { present: studentPresent, absent: studentAbsent })}
              disabled={loading}
              className="flex items-center gap-2 bg-blue-600 hover:bg-blue-700 disabled:bg-blue-400 text-white px-4 py-2 rounded-lg transition-colors"
            >
              <Save className="w-4 h-4" />
              Update Students
            </button>
          </div>

          {/* School Days Left */}
          <div className="space-y-4">
            <h3 className="text-lg font-semibold text-gray-800">School Days Left</h3>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">Days Remaining</label>
              <input
                type="number"
                value={daysLeft}
                onChange={(e) => setDaysLeft(Number(e.target.value))}
                className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"
              />
            </div>
            <button
              onClick={() => handleSave('daysLeft', daysLeft)}
              disabled={loading}
              className="flex items-center gap-2 bg-blue-600 hover:bg-blue-700 disabled:bg-blue-400 text-white px-4 py-2 rounded-lg transition-colors"
            >
              <Save className="w-4 h-4" />
              Update Days Left
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};