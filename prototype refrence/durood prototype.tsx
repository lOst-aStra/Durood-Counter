import React, { useState, useEffect } from 'react';
import { Plus, Minus, RotateCcw, Heart, Star, Moon } from 'lucide-react';

export default function DurudCounter() {
  const [currentSet, setCurrentSet] = useState(0);
  const [totalCount, setTotalCount] = useState(0);
  const [showResetConfirm, setShowResetConfirm] = useState(false);

  // Note: Counts are maintained during the session but will reset when page is refreshed

  const increment = () => {
    if (currentSet === 99) {
      setCurrentSet(0);
      setTotalCount(prev => prev + 1);
    } else {
      setCurrentSet(prev => prev + 1);
    }
  };

  const decrement = () => {
    if (currentSet === 0 && totalCount > 0) {
      setCurrentSet(99);
      setTotalCount(prev => prev - 1);
    } else if (currentSet > 0) {
      setCurrentSet(prev => prev - 1);
    }
  };

  const resetCounter = () => {
    setCurrentSet(0);
    setTotalCount(0);
    setShowResetConfirm(false);
    if (typeof window !== 'undefined' && window.sessionStorage) {
      window.sessionStorage.removeItem('durudCurrent');
      window.sessionStorage.removeItem('durudTotal');
    }
  };

  const handleResetClick = () => {
    setShowResetConfirm(true);
    setTimeout(() => setShowResetConfirm(false), 3000);
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-900 via-slate-800 to-slate-900 text-slate-100 flex items-center justify-center p-4">
      <div className="w-full max-w-md mx-auto">
        {/* Header */}
        <div className="text-center mb-8">
          <div className="flex items-center justify-center mb-6">
            <Moon className="w-8 h-8 text-emerald-400 mr-3" />
            <h1 className="text-3xl font-light tracking-wide text-slate-100">
              Durud Shareef
            </h1>
            <Star className="w-6 h-6 text-amber-400 ml-3" />
          </div>
          <div className="w-full px-4">
            <p className="text-slate-200 text-xl font-light leading-relaxed text-right" dir="rtl">
              اللَّهُمَّ صَلِّ عَلَىٰ مُحَمَّدٍ وَعَلَىٰ آلِ مُحَمَّدٍ وَبَارِكْ وَسَلِّمْ
            </p>
          </div>
        </div>

        {/* Main Counter Card */}
        <div className="bg-slate-800/50 backdrop-blur-sm rounded-3xl p-8 shadow-2xl border border-slate-700/50 mb-6">
          {/* Current Tasbeeh Display */}
          <div className="text-center mb-8">
            <h2 className="text-lg font-light text-slate-300 mb-2">Current Tasbeeh</h2>
            <div className="relative">
              <div className="text-6xl font-extralight text-emerald-400 mb-2">
                {currentSet.toString().padStart(2, '0')}
              </div>
              <div className="text-sm text-slate-500">/ 100</div>
              {/* Progress indicator */}
              <div className="w-full bg-slate-700 rounded-full h-1 mt-4">
                <div 
                  className="bg-gradient-to-r from-emerald-500 to-teal-400 h-1 rounded-full transition-all duration-300"
                  style={{ width: `${currentSet}%` }}
                ></div>
              </div>
            </div>
          </div>

          {/* Total Count Display */}
          <div className="text-center mb-8 p-4 bg-slate-900/30 rounded-2xl border border-slate-700/30">
            <h3 className="text-sm font-light text-slate-400 mb-1">Total Tasbeeh Completed</h3>
            <div className="flex items-center justify-center">
              <Heart className="w-5 h-5 text-rose-400 mr-2" />
              <span className="text-3xl font-light text-slate-200">
                {totalCount.toLocaleString()}
              </span>
            </div>
            <div className="text-xs text-slate-500 mt-1">
              Total recitations: {(totalCount * 100 + currentSet).toLocaleString()}
            </div>
          </div>

          {/* Control Buttons */}
          <div className="flex justify-between items-center px-8 mb-6">
            <button
              onClick={decrement}
              className="w-16 h-16 bg-slate-700/50 hover:bg-slate-600/50 rounded-full flex items-center justify-center transition-all duration-200 hover:scale-105 active:scale-95 border border-slate-600/30"
              disabled={currentSet === 0 && totalCount === 0}
            >
              <Minus className="w-6 h-6 text-slate-300" />
            </button>

            <button
              onClick={increment}
              className="w-20 h-20 bg-gradient-to-r from-emerald-500 to-teal-500 hover:from-emerald-400 hover:to-teal-400 rounded-full flex items-center justify-center transition-all duration-200 hover:scale-105 active:scale-95 shadow-lg shadow-emerald-500/25"
            >
              <Plus className="w-8 h-8 text-white" />
            </button>
          </div>

          {/* Quick Add Buttons */}
          <div className="flex justify-center space-x-3 mb-6">
            <button
              onClick={() => {
                const newCurrent = currentSet + 10;
                if (newCurrent >= 100) {
                  const completeSets = Math.floor(newCurrent / 100);
                  setTotalCount(prev => prev + completeSets);
                  setCurrentSet(newCurrent % 100);
                } else {
                  setCurrentSet(newCurrent);
                }
              }}
              className="px-4 py-2 bg-slate-700/30 hover:bg-slate-600/30 rounded-xl text-sm text-slate-300 transition-all duration-200 border border-slate-600/20"
            >
              +10
            </button>
            <button
              onClick={() => {
                const newCurrent = currentSet + 33;
                if (newCurrent >= 100) {
                  const completeSets = Math.floor(newCurrent / 100);
                  setTotalCount(prev => prev + completeSets);
                  setCurrentSet(newCurrent % 100);
                } else {
                  setCurrentSet(newCurrent);
                }
              }}
              className="px-4 py-2 bg-slate-700/30 hover:bg-slate-600/30 rounded-xl text-sm text-slate-300 transition-all duration-200 border border-slate-600/20"
            >
              +33
            </button>
          </div>
        </div>

        {/* Reset Button */}
        <div className="text-center">
          {!showResetConfirm ? (
            <button
              onClick={handleResetClick}
              className="px-6 py-3 bg-slate-700/30 hover:bg-slate-600/30 rounded-2xl text-slate-400 hover:text-slate-300 transition-all duration-200 border border-slate-600/20 text-sm font-light"
            >
              <RotateCcw className="w-4 h-4 inline mr-2" />
              Reset Session
            </button>
          ) : (
            <button
              onClick={resetCounter}
              className="px-6 py-3 bg-red-500/20 hover:bg-red-500/30 rounded-2xl text-red-400 hover:text-red-300 transition-all duration-200 border border-red-500/30 text-sm font-light animate-pulse"
            >
              <RotateCcw className="w-4 h-4 inline mr-2" />
              Confirm Reset
            </button>
          )}
        </div>

        {/* Footer */}
        <div className="text-center mt-8 text-xs text-slate-500">
          <p>May Allah accept your dhikr and grant you His blessings</p>
        </div>
      </div>
    </div>
  );
}