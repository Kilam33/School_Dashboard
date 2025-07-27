import React from 'react';

interface BehaviorData {
  positive: number;
  negative: number;
}

interface BehaviorDisplayProps {
  middleSchool: BehaviorData;
  elementary: BehaviorData;
}

export const BehaviorDisplay: React.FC<BehaviorDisplayProps> = ({ 
  middleSchool, 
  elementary 
}) => {
  return (
    <div className="space-y-6">
      <div className="bg-gradient-to-r from-blue-50 to-purple-50 rounded-lg p-4">
        <h4 className="font-semibold text-gray-800 mb-3">Middle School</h4>
        <div className="flex justify-between items-center">
          <div className="text-center">
            <div className="text-xl font-bold text-green-600">{middleSchool.positive}</div>
            <div className="text-xs text-gray-600">Positive</div>
          </div>
          <div className="text-center">
            <div className="text-xl font-bold text-red-500">{middleSchool.negative}</div>
            <div className="text-xs text-gray-600">Negative</div>
          </div>
        </div>
      </div>
      
      <div className="bg-gradient-to-r from-green-50 to-blue-50 rounded-lg p-4">
        <h4 className="font-semibold text-gray-800 mb-3">Elementary</h4>
        <div className="flex justify-between items-center">
          <div className="text-center">
            <div className="text-xl font-bold text-green-600">{elementary.positive}</div>
            <div className="text-xs text-gray-600">Positive</div>
          </div>
          <div className="text-center">
            <div className="text-xl font-bold text-red-500">{elementary.negative}</div>
            <div className="text-xs text-gray-600">Negative</div>
          </div>
        </div>
      </div>
    </div>
  );
};