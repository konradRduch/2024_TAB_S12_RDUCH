import React, { useState, useEffect } from "react";
import { Button } from "./ui/button";
import { cn } from "@/lib/utils";
/**
 * Hint interface represents a hint object.
 * @typedef {Object} Hint
 * @property {number} id - The unique identifier of the hint.
 * @property {string} text - The text content of the hint.
 * @property {number | null} next - The ID of the next hint, or null if it is the last hint.
 * @property {string} targetId - The ID of the target HTML element for the hint.
 */

interface Hint {
  id: number;
  text: string;
  next: number | null;
  targetId: string;
}

const hints: Hint[] = [
  {
    id: 1,
    text: 'Welcome! Let\'s start with validating your ticket. Click "Next" to continue.',
    next: 2,
    targetId: "validate-ticket",
  },
  {
    id: 2,
    text: 'Here you can buy tickets and passes. Click "Next" to continue.',
    next: 3,
    targetId: "store",
  },
  {
    id: 3,
    text: 'Here you can freeze your pass, to unfreeze it click on link in the description of the card "Click Here!". Click "Finish" to complete the tour.',
    next: 4,
    targetId: "freeze",
  },
  {
    id: 4,
    text: 'Here you can freeze your pass, to unfreeze it click on link in the description of the card "Click Here!". Click "Finish" to complete the tour.',
    next: null,
    targetId: "harmonogram",
  },
];

/**
 * Hints is a React functional component for displaying a guided tour with hints.
 * @component
 * @returns {JSX.Element | null} - The JSX code for Hints component or null if no hints are shown.
 */

const Hints: React.FC = () => {
  const [currentHint, setCurrentHint] = useState<Hint | null>(hints[0]);
  const [position, setPosition] = useState({ top: 0, left: 0, width: 0 });
  const [showOverlay, setShowOverlay] = useState(true);

  useEffect(() => {
    if (currentHint) {
      const targetElement = document.getElementById(currentHint.targetId);
      if (targetElement) {
        const rect = targetElement.getBoundingClientRect();
        setPosition({
          top: rect.top + window.scrollY,
          left: rect.left + window.scrollX,
          width: rect.width,
        });
      }
    }
  }, [currentHint]);

  /**
   * Handles the transition to the next hint.
   */
  const handleNext = () => {
    if (currentHint && currentHint.next !== null) {
      setCurrentHint(
        hints.find((hint) => hint.id === currentHint.next) || null
      );
    } else {
      // All hints shown, allow interaction
      setShowOverlay(false);
    }
  };

  /**
   * Handles the completion of the hints tour.
   */
  const handleFinish = () => {
    setCurrentHint(null);
    // Set a value in localStorage to indicate that hints have been shown
    localStorage.setItem("hintsShown", "true");
  };

  useEffect(() => {
    // Check if the hints have been shown before using localStorage
    const hintsShownBefore = localStorage.getItem("hintsShown");
    if (hintsShownBefore) {
      // Hints have been shown before, so do not show them again
      setCurrentHint(null);
      setShowOverlay(false); // Allow interaction if hints are already shown
    }
  }, []);

  if (!currentHint) {
    return null;
  }

  return (
    <div>
      {showOverlay && (
        <div className="fixed inset-0 bg-black opacity-50 z-40" />
      )}

      <div
        style={{
          position: "absolute",
          top: position.top,
          left: position.left,
          width: position.width,
          zIndex: 50,
        }}
        className="-translate-y-full bg-card text-card-foreground shadow-lg p-5"
      >
        <p>{currentHint.text}</p>
        {currentHint.next ? (
          <Button onClick={handleNext} className="mt-2">
            Next
          </Button>
        ) : (
          <Button onClick={handleFinish} className="mt-2">
            Finish
          </Button>
        )}
      </div>
    </div>
  );
};

export default Hints;
