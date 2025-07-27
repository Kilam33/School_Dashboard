import React from 'react';

interface AttendanceDisplayProps {
  present: number;
  absent: number;
  total?: number;
}

export const AttendanceDisplay: React.FC<AttendanceDisplayProps> = ({ 
  present, 
  absent, 
  total 
}) => {
  const calculatedTotal = total || (present + absent);
  const presentPercentage = calculatedTotal > 0 ? (present / calculatedTotal) * 100 : 0;

  return (
    <div className="space-y-4">
      <div className="flex justify-between items-center">
        <div className="text-center">
          <div className="text-2xl font-bold text-green-600">{present}</div>
          <div className="text-sm text-gray-600">Present</div>
        </div>
        <div className="text-center">
          <div className="text-2xl font-bold text-amber-600">{absent}</div>
          <div className="text-sm text-gray-600">Absent</div>
        </div>
        <div className="text-center">
          <div className="text-2xl font-bold text-gray-800">{calculatedTotal}</div>
          <div className="text-sm text-gray-600">Total</div>
        </div>
      </div>
      
      <div className="w-full bg-gray-200 rounded-full h-3">
        <div 
          className="bg-gradient-to-r from-green-500 to-green-600 h-3 rounded-full transition-all duration-500"
          style={{ width: `${presentPercentage}%` }}
        ></div>
      </div>
      
      <div className="text-center text-sm text-gray-600">
        {presentPercentage.toFixed(1)}% Present
      </div>
    </div>
  );
};