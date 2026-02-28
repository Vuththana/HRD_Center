import { use, useEffect, useState } from 'react'
import useBank from './hooks/useBank';

function App() {
  const {balance, dispatch} = useBank(1000);
  const [input, setInput] = useState("");
  const [error, setError] = useState("");
  return (
    <div className='flex flex-col justify-center items-center gap-[40px]'>
    <div >
      <p className='text-[30px] font-[700]'>My Bank Account</p>
    </div>
      <div className="flex flex-col gap-[20px]">
        <p>{error}</p>
        <p className='text-[18px] text-center font-[700]'>Balance: ${balance}</p>
        <div className='flex gap-[20px]'>
          <button className='px-[6px] py-[8px] bg-green-500 text-white rounded-[5px]' onClick={() => dispatch({type:'deposit', amount: parseInt(input)})}>Deposit ${input == 0 ? 0 : input}</button>
          <button className='px-[6px] py-[8px] bg-red-500 text-white rounded-[5px]' onClick={() => dispatch({type:'withdraw', amount: parseInt(input)})}>Withdraw ${input == 0 ? 0 : input}</button>
        </div>
        
      </div>
      <div>
        <label htmlFor="amount">Amount : </label>
        <input onChange={(e) => setInput(e.target.value)} className='border-2' type="text" name='amount'/>
      </div>
    </div>
  )
}

export default App
