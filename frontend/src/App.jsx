import { useState } from "react";

function App() {
  const [courses, setCourses] = useState("");
  const [standing, setStanding] = useState(1);
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleSubmit = async () => {
    setLoading(true);
    const completedCourses = courses.split(",").map(c => c.trim()).filter(Boolean);
    const res = await fetch("https://courseplanner-g78d.onrender.com/api/planner/recommend", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ completedCourses, standing: Number(standing) }),
    });
    const data = await res.json();
    setResult(data);
    setLoading(false);
  };

  return (
    <div style={{ padding: "2rem", fontFamily: "sans-serif" }}>
      <h1>Course Planner</h1>
      <label>Completed courses (comma-separated):</label>
      <input value={courses} onChange={e => setCourses(e.target.value)} style={{ width: "100%" }} />
      <label>Standing (1-8):</label>
      <input type="number" value={standing} onChange={e => setStanding(e.target.value)} />
      <button onClick={handleSubmit} disabled={loading}>
        {loading ? "Loading..." : "Get Recommendations"}
      </button>

      {result && (
        <div>
          <h2>Eligible Courses</h2>
          <ul>{result.eligibleCourses.map(c => <li key={c}>{c}</li>)}</ul>
          <h2>Recommended Courses</h2>
          <ul>{result.curatedCourses.map(c => <li key={c}>{c}</li>)}</ul>
        </div>
      )}
    </div>
  );
}

export default App;